package com.miasip.backend;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

    private PlayerStats getPlayerOrNull(String team, int number) {
        List<RosterPlayer> roster = result.rosters.get(team);
        if (roster != null && roster.stream().noneMatch(p -> p.number == number)) {
            logEvent(String.format(
                    "[%s] Ignored action: player #%d is not declared in the roster for team '%s'.",
                    getCurrentQuarterLabel(), number, team));
            return null;
        }
        result.stats.computeIfAbsent(team, t -> new TreeMap<>());
        return result.stats.get(team).computeIfAbsent(number, n -> new PlayerStats());
    }

    private String getCurrentQuarterLabel() {
        if (currentQuarter < 0) {
            return "Q1";
        }
        if (currentQuarter < 4) {
            return "Q" + (currentQuarter + 1);
        }
        return "OT";
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

            Map<Integer, PlayerStats> teamStats = result.stats.get(team);
            if (teamStats != null) {
                teamStats.putIfAbsent(num, new PlayerStats());
            }
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

        // short aliases: H for home, A for away (convenience for quick input)
        aliasMap.put("H", result.homeTeam);
        aliasMap.put("A", result.awayTeam);
        result.teamAliases.put("H", result.homeTeam);
        result.teamAliases.put("A", result.awayTeam);
        logEvent(String.format("Team alias: H → %s", result.homeTeam));
        logEvent(String.format("Team alias: A → %s", result.awayTeam));

        result.stats.put(result.homeTeam, new TreeMap<>());
        result.stats.put(result.awayTeam, new TreeMap<>());
        result.quarterScores.put(result.homeTeam, new ArrayList<>());
        result.quarterScores.put(result.awayTeam, new ArrayList<>());

        normalizeRosterTeams();

        // Ensure every declared roster player appears in the stats map
        // even if they have no actions. Roster entries may have been
        // parsed earlier (RULES section) or later; we initialize here
        // for any roster entries that exist for the full team name.
        List<RosterPlayer> hRoster = result.rosters.get(result.homeTeam);
        if (hRoster != null) {
            Map<Integer, PlayerStats> m = result.stats.get(result.homeTeam);
            for (RosterPlayer rp : hRoster) {
                m.putIfAbsent(rp.number, new PlayerStats());
            }
        }
        List<RosterPlayer> aRoster = result.rosters.get(result.awayTeam);
        if (aRoster != null) {
            Map<Integer, PlayerStats> m = result.stats.get(result.awayTeam);
            for (RosterPlayer rp : aRoster) {
                m.putIfAbsent(rp.number, new PlayerStats());
            }
        }

        logEvent("Game: " + result.homeTeam + " vs " + result.awayTeam);
        return null;
    }

    private void normalizeRosterTeams() {
        if (result.rosters.isEmpty()) {
            return;
        }

        Map<String, List<RosterPlayer>> normalized = new LinkedHashMap<>();
        for (Map.Entry<String, List<RosterPlayer>> entry : result.rosters.entrySet()) {
            String resolved = aliasMap.getOrDefault(entry.getKey(), entry.getKey());
            normalized.computeIfAbsent(resolved, t -> new ArrayList<>()).addAll(entry.getValue());
        }
        result.rosters.clear();
        result.rosters.putAll(normalized);
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
    public Void visitScore_made(ExprParser.Score_madeContext ctx) {
        ExprParser.Player_refContext ref = getPlayerRef(ctx);
        String team = getTeamName(ref);
        int num = Integer.parseInt(ref.INT().getText());
        PlayerStats p = getPlayerOrNull(team, num);
        if (p == null) {
            return null;
        }

        int points = 0;
        String op = ctx.getStart().getText();
        switch (op) {
            case "2pt":
                points = 2;
                p.pts += 2; p.fgm++; p.fga++;
                break;
            case "3pt":
                points = 3;
                p.pts += 3; p.fgm++; p.fga++; p.tpm++; p.tpa++;
                break;
            case "ft":
                points = 1;
                p.pts += 1; p.ftm++; p.fta++;
                break;
            default:
                break;
        }

        addQuarterScore(team, points);

        // optional assist attached to the scoring event (same team as scorer)
        ExprParser.AssistByContext assistBy = findAssistBy(ctx);
        if (assistBy != null) {
            String assistNumText = assistBy.getChild(1).getText();
            int aNum = Integer.parseInt(assistNumText);
            PlayerStats assistStats = getPlayerOrNull(team, aNum);
            if (assistStats != null) {
                assistStats.ast++;
            }
            logEvent(String.format("%s #%d %dpt (+%d) assisted by %s #%d", team, num, points, points, team, aNum));
        } else {
            logEvent(String.format("%s #%d %dpt (+%d)", team, num, points, points));
        }
        return null;
    }

    @Override
    public Void visitScore_missed(ExprParser.Score_missedContext ctx) {
        ExprParser.Player_refContext ref = getPlayerRef(ctx);
        String team = getTeamName(ref);
        int num = Integer.parseInt(ref.INT().getText());
        PlayerStats p = getPlayerOrNull(team, num);
        if (p == null) {
            return null;
        }

        String op = ctx.getStart().getText();
        switch (op) {
            case "2pt":
                p.fga++;
                break;
            case "3pt":
                p.fga++; p.tpa++;
                break;
            case "ft":
                p.fta++;
                break;
            default:
                break;
        }

        logEvent(String.format("%s #%d miss", team, num));
        return null;
    }

    // Helper: recursively find a nested assistBy inside a sub-tree
    private ExprParser.AssistByContext findAssistBy(org.antlr.v4.runtime.tree.ParseTree node) {
        if (node == null) return null;
        for (int i = 0; i < node.getChildCount(); i++) {
            org.antlr.v4.runtime.tree.ParseTree c = node.getChild(i);
            if (c instanceof ExprParser.AssistByContext) return (ExprParser.AssistByContext) c;
            ExprParser.AssistByContext found = findAssistBy(c);
            if (found != null) return found;
        }
        return null;
    }

    @Override
    public Void visitReb_off(ExprParser.Reb_offContext ctx) {
        ExprParser.Player_refContext ref = getPlayerRef(ctx);
        String team = getTeamName(ref);
        int num = Integer.parseInt(ref.INT().getText());
        PlayerStats p = getPlayerOrNull(team, num);
        if (p == null) {
            return null;
        }
        p.rebOff++;
        logEvent(String.format("%s #%d reb (OFF)", team, num));
        return null;
    }

    @Override
    public Void visitReb_def(ExprParser.Reb_defContext ctx) {
        ExprParser.Player_refContext ref = getPlayerRef(ctx);
        String team = getTeamName(ref);
        int num = Integer.parseInt(ref.INT().getText());
        PlayerStats p = getPlayerOrNull(team, num);
        if (p == null) {
            return null;
        }
        p.rebDef++;
        logEvent(String.format("%s #%d reb (DEF)", team, num));
        return null;
    }

    @Override
    public Void visitAssist(ExprParser.AssistContext ctx) {
        ExprParser.Player_refContext ref = getPlayerRef(ctx);
        String team = getTeamName(ref);
        int num = Integer.parseInt(ref.INT().getText());
        PlayerStats p = getPlayerOrNull(team, num);
        if (p == null) {
            return null;
        }
        p.ast++;
        logEvent(String.format("%s #%d AST", team, num));
        return null;
    }

    @Override
    public Void visitSteal(ExprParser.StealContext ctx) {
        ExprParser.Player_refContext ref = getPlayerRef(ctx);
        String team = getTeamName(ref);
        int num = Integer.parseInt(ref.INT().getText());
        PlayerStats p = getPlayerOrNull(team, num);
        if (p == null) {
            return null;
        }
        p.stl++;
        logEvent(String.format("%s #%d STL", team, num));
        return null;
    }

    @Override
    public Void visitBlock(ExprParser.BlockContext ctx) {
        ExprParser.Player_refContext ref = getPlayerRef(ctx);
        String team = getTeamName(ref);
        int num = Integer.parseInt(ref.INT().getText());
        PlayerStats p = getPlayerOrNull(team, num);
        if (p == null) {
            return null;
        }
        p.blk++;
        logEvent(String.format("%s #%d BLK", team, num));
        return null;
    }

    @Override
    public Void visitTurnover(ExprParser.TurnoverContext ctx) {
        ExprParser.Player_refContext ref = getPlayerRef(ctx);
        String team = getTeamName(ref);
        int num = Integer.parseInt(ref.INT().getText());
        PlayerStats p = getPlayerOrNull(team, num);
        if (p == null) {
            return null;
        }
        p.to++;
        logEvent(String.format("%s #%d TO", team, num));
        return null;
    }

    @Override
    public Void visitFoul(ExprParser.FoulContext ctx) {
        ExprParser.Player_refContext ref = getPlayerRef(ctx);
        String team = getTeamName(ref);
        int num = Integer.parseInt(ref.INT().getText());
        PlayerStats p = getPlayerOrNull(team, num);
        if (p == null) {
            return null;
        }
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