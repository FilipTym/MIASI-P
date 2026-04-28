package com.miasip.backend;
import java.util.*;

public class Visitor extends ExprParserBaseVisitor<Void> {

    // -------------------------------------------------------------------------
    // Data model
    // -------------------------------------------------------------------------

    public static class PlayerStats {
        public int pts = 0;
        public int fgm = 0, fga = 0;
        public int tpm = 0, tpa = 0;
        public int ftm = 0, fta = 0;
        public int rebOff = 0, rebDef = 0;
        public int ast = 0, stl = 0, blk = 0, to = 0;
        public int foulsPersonal = 0, foulsTechnical = 0, foulsFlagrant = 0;
    }

    public static class RosterPlayer {
        public int number;
        public RosterPlayer(int number) { this.number = number; }
    }

    public static class GameResult {
        public String homeTeam;
        public String awayTeam;
        /** alias → full team name, e.g. "LAL" → "Lakers". Also sent to frontend for autocomplete. */
        public Map<String, String> teamAliases = new LinkedHashMap<>();
        /** full team name → list of declared jersey numbers */
        public Map<String, List<RosterPlayer>> rosters = new LinkedHashMap<>();
        /** full team name → jersey number → stats */
        public Map<String, Map<Integer, PlayerStats>> stats = new LinkedHashMap<>();
        /** quarter scores per team */
        public Map<String, List<Integer>> quarterScores = new LinkedHashMap<>();
        public List<String> events = new ArrayList<>();
        public List<String> errors  = new ArrayList<>();
    }

    private final GameResult result = new GameResult();
    private int currentQuarter = 0;

    /** alias → full name (and full → full for uniform lookups) */
    private final Map<String, String> aliasMap = new HashMap<>();

    public GameResult getResult() { return result; }

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------

    private String resolveTeam(String token) {
        String resolved = aliasMap.get(token);
        if (resolved == null) {
            result.errors.add("Unknown team reference: '" + token + "'. " +
                    "Use the full team name or the alias declared in the GAME header.");
            return token;
        }
        return resolved;
    }

    private PlayerStats getPlayer(String team, int number) {
        List<RosterPlayer> roster = result.rosters.get(team);
        if (roster != null && roster.stream().noneMatch(p -> p.number == number)) {
            result.errors.add(String.format(
                    "Player #%d is not declared in the roster for team '%s'.", number, team));
        }
        result.stats.computeIfAbsent(team, t -> new TreeMap<>());
        return result.stats.get(team).computeIfAbsent(number, n -> new PlayerStats());
    }

    private void addQuarterScore(String team, int pts) {
        List<Integer> qs = result.quarterScores.get(team);
        if (qs == null) return;
        while (qs.size() <= currentQuarter) qs.add(0);
        qs.set(currentQuarter, qs.get(currentQuarter) + pts);
    }

    private void logEvent(String msg) { result.events.add(msg); }

    private String getTeamName(ExprParser.Player_refContext ref) {
        return resolveTeam(ref.teamRef().getText());
    }

    // -------------------------------------------------------------------------
    // Visitors — RULES section
    // -------------------------------------------------------------------------

    @Override
    public Void visitRulesSection(ExprParser.RulesSectionContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Void visitRuleDefItem(ExprParser.RuleDefItemContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Void visitRosterDefItem(ExprParser.RosterDefItemContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * ROSTER Lakers: #5, #23, #3;
     * Registers players. Uses the raw team token directly because RULES typically
     * comes before the GAME header (aliases not yet known). If it comes after,
     * aliasMap.getOrDefault falls back to the full name anyway.
     */
    @Override
    public Void visitRosterDef(ExprParser.RosterDefContext ctx) {
        String rawTeam = ctx.teamRef().getText();
        String team = aliasMap.getOrDefault(rawTeam, rawTeam);

        result.rosters.computeIfAbsent(team, t -> new ArrayList<>());
        List<RosterPlayer> roster = result.rosters.get(team);

        for (ExprParser.PlayerEntryContext pe : ctx.playerEntry()) {
            int num = Integer.parseInt(pe.INT().getText());
            roster.add(new RosterPlayer(num));
            logEvent(String.format("Roster %s: #%d", team, num));
        }
        return null;
    }

    // -------------------------------------------------------------------------
    // Visitors — GAME header
    // -------------------------------------------------------------------------

    @Override
    public Void visitGame(ExprParser.GameContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * GAME Lakers as LAL vs Celtics as BOS;
     * Builds aliasMap: full→full and alias→full.
     */
    @Override
    public Void visitHeader(ExprParser.HeaderContext ctx) {
        ExprParser.TeamDeclContext homeDecl = ctx.teamDecl(0);
        ExprParser.TeamDeclContext awayDecl = ctx.teamDecl(1);

        result.homeTeam = homeDecl.teamRef().getText();
        result.awayTeam = awayDecl.teamRef().getText();

        registerTeam(result.homeTeam, homeDecl.teamAlias());
        registerTeam(result.awayTeam, awayDecl.teamAlias());

        result.stats.put(result.homeTeam, new TreeMap<>());
        result.stats.put(result.awayTeam, new TreeMap<>());
        result.quarterScores.put(result.homeTeam, new ArrayList<>());
        result.quarterScores.put(result.awayTeam, new ArrayList<>());

        logEvent("Game: " + result.homeTeam + " vs " + result.awayTeam);
        return null;
    }

    private void registerTeam(String fullName, ExprParser.TeamAliasContext aliasCtx) {
        aliasMap.put(fullName, fullName);
        if (aliasCtx != null) {
            String alias = aliasCtx.getText();
            aliasMap.put(alias, fullName);
            result.teamAliases.put(alias, fullName);
            logEvent(String.format("Team alias: %s → %s", alias, fullName));
        }
    }

    // -------------------------------------------------------------------------
    // Visitors — quarters & actions
    // -------------------------------------------------------------------------

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

    @Override
    public Void visitMiss(ExprParser.MissContext ctx) {
        ExprParser.Player_refContext ref = getPlayerRef(ctx);
        String team = getTeamName(ref);
        int num = Integer.parseInt(ref.INT().getText());
        getPlayer(team, num).fga++;
        logEvent(String.format("%s #%d miss", team, num));
        return null;
    }

    @Override
    public Void visitReb_off(ExprParser.Reb_offContext ctx) {
        ExprParser.Player_refContext ref = getPlayerRef(ctx);
        String team = getTeamName(ref);
        int num = Integer.parseInt(ref.INT().getText());
        getPlayer(team, num).rebOff++;
        logEvent(String.format("%s #%d reb (OFF)", team, num));
        return null;
    }

    @Override
    public Void visitReb_def(ExprParser.Reb_defContext ctx) {
        ExprParser.Player_refContext ref = getPlayerRef(ctx);
        String team = getTeamName(ref);
        int num = Integer.parseInt(ref.INT().getText());
        getPlayer(team, num).rebDef++;
        logEvent(String.format("%s #%d reb (DEF)", team, num));
        return null;
    }

    @Override
    public Void visitAssist(ExprParser.AssistContext ctx) {
        ExprParser.Player_refContext ref = getPlayerRef(ctx);
        String team = getTeamName(ref);
        int num = Integer.parseInt(ref.INT().getText());
        getPlayer(team, num).ast++;
        logEvent(String.format("%s #%d AST", team, num));
        return null;
    }

    @Override
    public Void visitSteal(ExprParser.StealContext ctx) {
        ExprParser.Player_refContext ref = getPlayerRef(ctx);
        String team = getTeamName(ref);
        int num = Integer.parseInt(ref.INT().getText());
        getPlayer(team, num).stl++;
        logEvent(String.format("%s #%d STL", team, num));
        return null;
    }

    @Override
    public Void visitBlock(ExprParser.BlockContext ctx) {
        ExprParser.Player_refContext ref = getPlayerRef(ctx);
        String team = getTeamName(ref);
        int num = Integer.parseInt(ref.INT().getText());
        getPlayer(team, num).blk++;
        logEvent(String.format("%s #%d BLK", team, num));
        return null;
    }

    @Override
    public Void visitTurnover(ExprParser.TurnoverContext ctx) {
        ExprParser.Player_refContext ref = getPlayerRef(ctx);
        String team = getTeamName(ref);
        int num = Integer.parseInt(ref.INT().getText());
        getPlayer(team, num).to++;
        logEvent(String.format("%s #%d TO", team, num));
        return null;
    }

    @Override
    public Void visitFoul(ExprParser.FoulContext ctx) {
        ExprParser.Player_refContext ref = getPlayerRef(ctx);
        String team = getTeamName(ref);
        int num = Integer.parseInt(ref.INT().getText());
        PlayerStats p = getPlayer(team, num);
        ExprParser.Foul_actionContext fa = ctx.foul_action();
        String type;
        if      (fa.FOUL_P() != null) { p.foulsPersonal++;  type = "FOUL (personal)"; }
        else if (fa.FOUL_T() != null) { p.foulsTechnical++; type = "FOUL (technical)"; }
        else                          { p.foulsFlagrant++;  type = "FOUL (flagrant)"; }
        logEvent(String.format("%s #%d %s", team, num, type));
        return null;
    }

    @Override
    public Void visitBoxscore_cmd(ExprParser.Boxscore_cmdContext ctx) {
        logEvent("BOXSCORE generated");
        return null;
    }

    // -------------------------------------------------------------------------
    // Helper — walk up the tree to the enclosing PlayerEventContext
    // -------------------------------------------------------------------------

    private ExprParser.Player_refContext getPlayerRef(org.antlr.v4.runtime.tree.ParseTree actionCtx) {
        org.antlr.v4.runtime.tree.ParseTree node = actionCtx;
        while (node != null && !(node instanceof ExprParser.PlayerEventContext)) {
            node = node.getParent();
        }
        if (node == null)
            throw new RuntimeException("PlayerEventContext not found above: " + actionCtx.getText());
        return ((ExprParser.PlayerEventContext) node).player_ref();
    }
}