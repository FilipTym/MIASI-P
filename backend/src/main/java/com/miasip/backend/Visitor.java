package com.miasip.backend;
import java.util.*;

// Visitor przechodzi drzewo parsowania ANTLR i zbiera statystyki meczu.
// Każda metoda visitXxx odpowiada jednemu węzłowi w gramatyce ExprParser.
public class Visitor extends ExprParserBaseVisitor<Void> {

    // -------------------------------------------------------------------------
    // Model danych (public żeby Jackson mógł serializować do JSON)
    // -------------------------------------------------------------------------

    // Statystyki jednego gracza akumulowane przez cały mecz.
    public static class PlayerStats {
        public int pts = 0;
        public int fgm = 0, fga = 0;   // rzuty z gry: trafione / próby (2pt + 3pt)
        public int tpm = 0, tpa = 0;   // trójki: trafione / próby
        public int ftm = 0, fta = 0;   // rzuty wolne: trafione / próby
        public int rebOff = 0, rebDef = 0;
        public int ast = 0, stl = 0, blk = 0, to = 0;
        public int foulsPersonal = 0, foulsTechnical = 0, foulsFlagrant = 0;
    }

    // Kompletny wynik meczu zwracany przez REST endpoint jako JSON.
    public static class GameResult {
        public String homeTeam;
        public String awayTeam;
        public Map<String, Map<Integer, PlayerStats>> stats = new LinkedHashMap<>(); // stats[drużyna][numer] → PlayerStats
        public Map<String, List<Integer>> quarterScores = new LinkedHashMap<>();     // wyniki kwartowe per drużyna
        public List<String> events = new ArrayList<>();  // log wydarzeń widoczny w Events Log
        public List<String> errors = new ArrayList<>();  // błędy parsowania
    }

    private final GameResult result = new GameResult();
    private int currentQuarter = 0; // indeks aktualnej kwarty (0-based)

    // Zwraca zbudowany wynik — wywoływane przez BoxscoreController po visitor.visit(tree).
    public GameResult getResult() {
        return result;
    }

    // -------------------------------------------------------------------------
    // Metody pomocnicze
    // -------------------------------------------------------------------------

    // Zwraca (tworząc jeśli trzeba) obiekt statystyk dla gracza o danym numerze w danej drużynie.
    private PlayerStats getPlayer(String team, int number) {
        result.stats.computeIfAbsent(team, t -> new TreeMap<>());
        return result.stats.get(team).computeIfAbsent(number, n -> new PlayerStats());
    }

    // Dodaje punkty do wyniku kwartowego wskazanej drużyny.
    private void addQuarterScore(String team, int pts) {
        List<Integer> qs = result.quarterScores.get(team);
        while (qs.size() <= currentQuarter) qs.add(0);
        qs.set(currentQuarter, qs.get(currentQuarter) + pts);
    }

    // Dodaje wpis do logu wydarzeń widocznego na froncie.
    private void logEvent(String message) {
        result.events.add(message);
    }

    // Wyciąga nazwę drużyny z węzła player_ref (np. HOME, AWAY lub dowolne ID).
    private String getTeamName(ExprParser.Player_refContext ref) {
        return ref.team_name().getText();
    }

    // -------------------------------------------------------------------------
    // Visitory węzłów gramatyki
    // -------------------------------------------------------------------------

    // Węzeł główny meczu — deleguje przetwarzanie do dzieci (header + kwarty).
    @Override
    public Void visitGame(ExprParser.GameContext ctx) {
        return visitChildren(ctx);
    }

    // Nagłówek "GAME X vs Y;" — inicjalizuje nazwy drużyn i puste struktury danych.
    @Override
    public Void visitHeader(ExprParser.HeaderContext ctx) {
        result.homeTeam = ctx.team_name(0).getText();
        result.awayTeam = ctx.team_name(1).getText();

        result.stats.put(result.homeTeam, new TreeMap<>());
        result.stats.put(result.awayTeam, new TreeMap<>());
        result.quarterScores.put(result.homeTeam, new ArrayList<>());
        result.quarterScores.put(result.awayTeam, new ArrayList<>());

        logEvent("Game: " + result.homeTeam + " vs " + result.awayTeam);
        return null;
    }

    // Kwarta "QUARTER N ... END;" — ustawia currentQuarter i przetwarza wszystkie wydarzenia w kwarcie.
    @Override
    public Void visitQuarter(ExprParser.QuarterContext ctx) {
        String label = ctx.OT() != null ? "OT" : "Q" + ctx.INT().getText();
        logEvent("--- " + label + " ---");
        currentQuarter = result.quarterScores.get(result.homeTeam).size();
        result.quarterScores.get(result.homeTeam).add(0);
        result.quarterScores.get(result.awayTeam).add(0);
        visitChildren(ctx);
        return null;
    }

    // Rzut za 2 punkty — dodaje 2 pkt, zalicza trafiony rzut z gry.
    @Override
    public Void visitScore_2pt(ExprParser.Score_2ptContext ctx) {
        ExprParser.Player_refContext ref = getPlayerRef(ctx);
        String team = getTeamName(ref);
        int num = Integer.parseInt(ref.INT().getText());
        PlayerStats p = getPlayer(team, num);
        p.pts += 2; p.fgm++; p.fga++;
        addQuarterScore(team, 2);
        logEvent(String.format("%s #%d 2pt (+2)", team, num));
        return null;
    }

    // Rzut za 3 punkty — dodaje 3 pkt, zalicza trafiony rzut z gry i trójkę.
    @Override
    public Void visitScore_3pt(ExprParser.Score_3ptContext ctx) {
        ExprParser.Player_refContext ref = getPlayerRef(ctx);
        String team = getTeamName(ref);
        int num = Integer.parseInt(ref.INT().getText());
        PlayerStats p = getPlayer(team, num);
        p.pts += 3; p.fgm++; p.fga++; p.tpm++; p.tpa++;
        addQuarterScore(team, 3);
        logEvent(String.format("%s #%d 3pt (+3)", team, num));
        return null;
    }

    // Trafiony rzut wolny — dodaje 1 pkt, zalicza trafiony rzut wolny.
    @Override
    public Void visitScore_ft(ExprParser.Score_ftContext ctx) {
        ExprParser.Player_refContext ref = getPlayerRef(ctx);
        String team = getTeamName(ref);
        int num = Integer.parseInt(ref.INT().getText());
        PlayerStats p = getPlayer(team, num);
        p.pts++; p.ftm++; p.fta++;
        addQuarterScore(team, 1);
        logEvent(String.format("%s #%d FT (+1)", team, num));
        return null;
    }

    // Niecelny rzut — zalicza tylko próbę rzutu z gry (bez punktów).
    @Override
    public Void visitMiss(ExprParser.MissContext ctx) {
        ExprParser.Player_refContext ref = getPlayerRef(ctx);
        String team = getTeamName(ref);
        int num = Integer.parseInt(ref.INT().getText());
        getPlayer(team, num).fga++;
        logEvent(String.format("%s #%d miss", team, num));
        return null;
    }

    // Zbiórka ofensywna — gracz zdobył piłkę po niecelnym rzucie własnej drużyny.
    @Override
    public Void visitReb_off(ExprParser.Reb_offContext ctx) {
        ExprParser.Player_refContext ref = getPlayerRef(ctx);
        String team = getTeamName(ref);
        int num = Integer.parseInt(ref.INT().getText());
        getPlayer(team, num).rebOff++;
        logEvent(String.format("%s #%d reb (OFF)", team, num));
        return null;
    }

    // Zbiórka defensywna — gracz zdobył piłkę po niecelnym rzucie przeciwnika.
    @Override
    public Void visitReb_def(ExprParser.Reb_defContext ctx) {
        ExprParser.Player_refContext ref = getPlayerRef(ctx);
        String team = getTeamName(ref);
        int num = Integer.parseInt(ref.INT().getText());
        getPlayer(team, num).rebDef++;
        logEvent(String.format("%s #%d reb (DEF)", team, num));
        return null;
    }

    // Asysta — gracz zanotował podanie prowadzące bezpośrednio do trafionego rzutu.
    @Override
    public Void visitAssist(ExprParser.AssistContext ctx) {
        ExprParser.Player_refContext ref = getPlayerRef(ctx);
        String team = getTeamName(ref);
        int num = Integer.parseInt(ref.INT().getText());
        getPlayer(team, num).ast++;
        logEvent(String.format("%s #%d AST", team, num));
        return null;
    }

    // Przechwyt — gracz odebrał piłkę przeciwnikowi.
    @Override
    public Void visitSteal(ExprParser.StealContext ctx) {
        ExprParser.Player_refContext ref = getPlayerRef(ctx);
        String team = getTeamName(ref);
        int num = Integer.parseInt(ref.INT().getText());
        getPlayer(team, num).stl++;
        logEvent(String.format("%s #%d STL", team, num));
        return null;
    }

    // Blok — gracz zablokował rzut przeciwnika.
    @Override
    public Void visitBlock(ExprParser.BlockContext ctx) {
        ExprParser.Player_refContext ref = getPlayerRef(ctx);
        String team = getTeamName(ref);
        int num = Integer.parseInt(ref.INT().getText());
        getPlayer(team, num).blk++;
        logEvent(String.format("%s #%d BLK", team, num));
        return null;
    }

    // Strata — gracz stracił piłkę (np. złe podanie, wyjście za linię).
    @Override
    public Void visitTurnover(ExprParser.TurnoverContext ctx) {
        ExprParser.Player_refContext ref = getPlayerRef(ctx);
        String team = getTeamName(ref);
        int num = Integer.parseInt(ref.INT().getText());
        getPlayer(team, num).to++;
        logEvent(String.format("%s #%d TO", team, num));
        return null;
    }

    // Faul — rozróżnia typ faulu (personalny / techniczny / flagrant) i aktualizuje odpowiedni licznik.
    @Override
    public Void visitFoul(ExprParser.FoulContext ctx) {
        ExprParser.Player_refContext ref = getPlayerRef(ctx);
        String team = getTeamName(ref);
        int num = Integer.parseInt(ref.INT().getText());
        PlayerStats p = getPlayer(team, num);
        ExprParser.Foul_actionContext fa = ctx.foul_action();
        String type;
        if (fa.FOUL_P() != null) { p.foulsPersonal++;  type = "FOUL (personal)"; }
        else if (fa.FOUL_T() != null) { p.foulsTechnical++; type = "FOUL (technical)"; }
        else { p.foulsFlagrant++; type = "FOUL (flagrant)"; }
        logEvent(String.format("%s #%d %s", team, num, type));
        return null;
    }

    // Komenda BOXSCORE — sygnalizuje koniec wejścia, dodaje wpis do logu.
    @Override
    public Void visitBoxscore_cmd(ExprParser.Boxscore_cmdContext ctx) {
        logEvent("BOXSCORE generated");
        return null;
    }

    // Pomocnicza metoda wyciągająca węzeł player_ref dla danej akcji.
    // Wspina się po drzewie w górę aż do węzła PlayerEventContext,
    // który zawiera zarówno player_ref (kto) jak i action (co zrobił).
    private ExprParser.Player_refContext getPlayerRef(org.antlr.v4.runtime.tree.ParseTree actionCtx) {
        org.antlr.v4.runtime.tree.ParseTree node = actionCtx;
        while (node != null && !(node instanceof ExprParser.PlayerEventContext)) {
            node = node.getParent();
        }
        if (node == null)
            throw new RuntimeException("PlayerEventContext not found above: " + actionCtx.getText());

        ExprParser.PlayerEventContext eventCtx = (ExprParser.PlayerEventContext) node;
        return eventCtx.player_ref();
    }
}