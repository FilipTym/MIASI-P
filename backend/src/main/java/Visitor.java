import java.util.*;

public class Visitor extends ExprParserBaseVisitor<Void> {

    // -------------------------------------------------------------------------
    // Data model
    // -------------------------------------------------------------------------

    private static class PlayerStats {
        int pts = 0;
        int fgm = 0, fga = 0;   // field goals made / attempted (2pt + 3pt)
        int tpm = 0, tpa = 0;   // three pointers made / attempted
        int ftm = 0, fta = 0;   // free throws made / attempted
        int rebOff = 0, rebDef = 0;
        int ast = 0, stl = 0, blk = 0, to = 0;
        int foulsPersonal = 0, foulsTechnical = 0, foulsFlagrant = 0;
    }

    // stats[team][playerNumber] → PlayerStats
    private final Map<String, Map<Integer, PlayerStats>> stats = new LinkedHashMap<>();

    // team scores per quarter: scores[team][quarterIndex]
    private final Map<String, List<Integer>> quarterScores = new LinkedHashMap<>();

    private int currentQuarter = 0;

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------

    private PlayerStats getPlayer(String team, int number) {
        stats.computeIfAbsent(team, t -> new TreeMap<>());
        return stats.get(team).computeIfAbsent(number, n -> new PlayerStats());
    }

    private void addQuarterScore(String team, int pts) {
        List<Integer> qs = quarterScores.get(team);
        while (qs.size() <= currentQuarter) qs.add(0);
        qs.set(currentQuarter, qs.get(currentQuarter) + pts);
    }

    // -------------------------------------------------------------------------
    // Visitors
    // -------------------------------------------------------------------------

    @Override
    public Void visitGame(ExprParser.GameContext ctx) {
        stats.put("HOME", new TreeMap<>());
        stats.put("AWAY", new TreeMap<>());
        quarterScores.put("HOME", new ArrayList<>());
        quarterScores.put("AWAY", new ArrayList<>());
        return visitChildren(ctx);
    }

    @Override
    public Void visitHeader(ExprParser.HeaderContext ctx) {
        System.out.println("Game: HOME vs AWAY");
        System.out.println("─".repeat(60));
        return null;
    }

    @Override
    public Void visitQuarter(ExprParser.QuarterContext ctx) {
        String label = ctx.OT() != null ? "OT" : "Q" + ctx.INT().getText();
        System.out.println("\n--- " + label + " ---");
        currentQuarter = quarterScores.get("HOME").size();
        quarterScores.get("HOME").add(0);
        quarterScores.get("AWAY").add(0);
        visitChildren(ctx);
        return null;
    }

    @Override
    public Void visitEvent(ExprParser.EventContext ctx) {
        return visitChildren(ctx);
    }

    // --- Scoring ---

    @Override
    public Void visitScore_2pt(ExprParser.Score_2ptContext ctx) {
        ExprParser.Player_refContext ref = getPlayerRef(ctx);
        String team = ref.HOME() != null ? "HOME" : "AWAY";
        int num = Integer.parseInt(ref.INT().getText());
        PlayerStats p = getPlayer(team, num);
        p.pts += 2; p.fgm++; p.fga++;
        addQuarterScore(team, 2);
        System.out.printf("  %s #%d  2pt  (+2)%n", team, num);
        return null;
    }

    @Override
    public Void visitScore_3pt(ExprParser.Score_3ptContext ctx) {
        ExprParser.Player_refContext ref = getPlayerRef(ctx);
        String team = ref.HOME() != null ? "HOME" : "AWAY";
        int num = Integer.parseInt(ref.INT().getText());
        PlayerStats p = getPlayer(team, num);
        p.pts += 3; p.fgm++; p.fga++; p.tpm++; p.tpa++;
        addQuarterScore(team, 3);
        System.out.printf("  %s #%d  3pt  (+3)%n", team, num);
        return null;
    }

    @Override
    public Void visitScore_ft(ExprParser.Score_ftContext ctx) {
        ExprParser.Player_refContext ref = getPlayerRef(ctx);
        String team = ref.HOME() != null ? "HOME" : "AWAY";
        int num = Integer.parseInt(ref.INT().getText());
        PlayerStats p = getPlayer(team, num);
        p.pts++; p.ftm++; p.fta++;
        addQuarterScore(team, 1);
        System.out.printf("  %s #%d  FT   (+1)%n", team, num);
        return null;
    }

    @Override
    public Void visitMiss(ExprParser.MissContext ctx) {
        ExprParser.Player_refContext ref = getPlayerRef(ctx);
        String team = ref.HOME() != null ? "HOME" : "AWAY";
        int num = Integer.parseInt(ref.INT().getText());
        PlayerStats p = getPlayer(team, num);
        p.fga++;
        System.out.printf("  %s #%d  miss%n", team, num);
        return null;
    }

    // --- Stats ---

    @Override
    public Void visitReb_off(ExprParser.Reb_offContext ctx) {
        ExprParser.Player_refContext ref = getPlayerRef(ctx);
        String team = ref.HOME() != null ? "HOME" : "AWAY";
        int num = Integer.parseInt(ref.INT().getText());
        getPlayer(team, num).rebOff++;
        System.out.printf("  %s #%d  reb (OFF)%n", team, num);
        return null;
    }

    @Override
    public Void visitReb_def(ExprParser.Reb_defContext ctx) {
        ExprParser.Player_refContext ref = getPlayerRef(ctx);
        String team = ref.HOME() != null ? "HOME" : "AWAY";
        int num = Integer.parseInt(ref.INT().getText());
        getPlayer(team, num).rebDef++;
        System.out.printf("  %s #%d  reb (DEF)%n", team, num);
        return null;
    }

    @Override
    public Void visitAssist(ExprParser.AssistContext ctx) {
        ExprParser.Player_refContext ref = getPlayerRef(ctx);
        String team = ref.HOME() != null ? "HOME" : "AWAY";
        int num = Integer.parseInt(ref.INT().getText());
        getPlayer(team, num).ast++;
        System.out.printf("  %s #%d  AST%n", team, num);
        return null;
    }

    @Override
    public Void visitSteal(ExprParser.StealContext ctx) {
        ExprParser.Player_refContext ref = getPlayerRef(ctx);
        String team = ref.HOME() != null ? "HOME" : "AWAY";
        int num = Integer.parseInt(ref.INT().getText());
        getPlayer(team, num).stl++;
        System.out.printf("  %s #%d  STL%n", team, num);
        return null;
    }

    @Override
    public Void visitBlock(ExprParser.BlockContext ctx) {
        ExprParser.Player_refContext ref = getPlayerRef(ctx);
        String team = ref.HOME() != null ? "HOME" : "AWAY";
        int num = Integer.parseInt(ref.INT().getText());
        getPlayer(team, num).blk++;
        System.out.printf("  %s #%d  BLK%n", team, num);
        return null;
    }

    @Override
    public Void visitTurnover(ExprParser.TurnoverContext ctx) {
        ExprParser.Player_refContext ref = getPlayerRef(ctx);
        String team = ref.HOME() != null ? "HOME" : "AWAY";
        int num = Integer.parseInt(ref.INT().getText());
        getPlayer(team, num).to++;
        System.out.printf("  %s #%d  TO%n", team, num);
        return null;
    }

    // --- Fouls ---

    @Override
    public Void visitFoul(ExprParser.FoulContext ctx) {
        ExprParser.Player_refContext ref = getPlayerRef(ctx);
        String team = ref.HOME() != null ? "HOME" : "AWAY";
        int num = Integer.parseInt(ref.INT().getText());
        PlayerStats p = getPlayer(team, num);
        ExprParser.Foul_actionContext fa = ctx.foul_action();
        String type;
        if (fa.FOUL_P() != null) { p.foulsPersonal++;  type = "FOUL (personal)"; }
        else if (fa.FOUL_T() != null) { p.foulsTechnical++; type = "FOUL (technical)"; }
        else { p.foulsFlagrant++; type = "FOUL (flagrant)"; }
        System.out.printf("  %s #%d  %s%n", team, num, type);
        return null;
    }

    // --- Boxscore ---

    @Override
    public Void visitBoxscore_cmd(ExprParser.Boxscore_cmdContext ctx) {
        printBoxscore();
        return null;
    }

    // -------------------------------------------------------------------------
    // Boxscore output
    // -------------------------------------------------------------------------

    private void printBoxscore() {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("  B O X S C O R E");
        System.out.println("═".repeat(60));

        // Quarter scores
        List<Integer> homeQ = quarterScores.get("HOME");
        List<Integer> awayQ = quarterScores.get("AWAY");
        int homeTotal = homeQ.stream().mapToInt(Integer::intValue).sum();
        int awayTotal = awayQ.stream().mapToInt(Integer::intValue).sum();

        StringBuilder qHeader = new StringBuilder(String.format("  %-8s", ""));
        StringBuilder homeLine = new StringBuilder(String.format("  %-8s", "HOME"));
        StringBuilder awayLine = new StringBuilder(String.format("  %-8s", "AWAY"));
        for (int i = 0; i < homeQ.size(); i++) {
            String label = (i < 4) ? "Q" + (i + 1) : "OT";
            qHeader.append(String.format(" %-4s", label));
            homeLine.append(String.format(" %-4d", homeQ.get(i)));
            awayLine.append(String.format(" %-4d", awayQ.get(i)));
        }
        qHeader.append(String.format(" | %s", "TOT"));
        homeLine.append(String.format(" | %d", homeTotal));
        awayLine.append(String.format(" | %d", awayTotal));

        System.out.println(qHeader);
        System.out.println(homeLine);
        System.out.println(awayLine);
        System.out.println("─".repeat(60));

        // Per-team player stats
        for (String team : List.of("HOME", "AWAY")) {
            System.out.printf("%n  %s%n", team);
            System.out.printf("  %-6s %4s %6s %5s %5s %5s %4s %4s %4s %4s %4s %4s%n",
                    "#", "PTS", "FG", "3P", "FT", "REB", "AST", "STL", "BLK", "TO", "PF", "TF");
            System.out.println("  " + "─".repeat(56));

            Map<Integer, PlayerStats> teamStats = stats.get(team);
            if (teamStats == null || teamStats.isEmpty()) {
                System.out.println("  (no events)");
                continue;
            }

            int tPts=0, tFgm=0, tFga=0, tTpm=0, tTpa=0, tFtm=0, tFta=0;
            int tReb=0, tAst=0, tStl=0, tBlk=0, tTo=0, tPf=0;

            for (Map.Entry<Integer, PlayerStats> e : teamStats.entrySet()) {
                int num = e.getKey();
                PlayerStats p = e.getValue();
                String fg = p.fgm + "/" + p.fga;
                String tp = p.tpm + "/" + p.tpa;
                String ft = p.ftm + "/" + p.fta;
                int reb = p.rebOff + p.rebDef;
                System.out.printf("  %-6d %4d %6s %5s %5s %5d %4d %4d %4d %4d %4d %4d%n",
                        num, p.pts, fg, tp, ft, reb,
                        p.ast, p.stl, p.blk, p.to, p.foulsPersonal, p.foulsTechnical);
                tPts += p.pts; tFgm += p.fgm; tFga += p.fga;
                tTpm += p.tpm; tTpa += p.tpa;
                tFtm += p.ftm; tFta += p.fta;
                tReb += reb; tAst += p.ast; tStl += p.stl;
                tBlk += p.blk; tTo += p.to; tPf += p.foulsPersonal;
            }
            System.out.println("  " + "─".repeat(56));
            System.out.printf("  %-6s %4d %6s %5s %5s %5d %4d %4d %4d %4d %4d%n",
                    "TOT", tPts,
                    tFgm + "/" + tFga, tTpm + "/" + tTpa, tFtm + "/" + tFta,
                    tReb, tAst, tStl, tBlk, tTo, tPf);
        }
        System.out.println("\n" + "═".repeat(60));
    }

    // -------------------------------------------------------------------------
    // Utility: walk up the tree to find the nearest player_ref sibling
    // -------------------------------------------------------------------------

    private ExprParser.Player_refContext getPlayerRef(
            org.antlr.v4.runtime.tree.ParseTree actionCtx) {
        // Walk up until we reach the EventContext, then search its children
        org.antlr.v4.runtime.tree.ParseTree node = actionCtx;
        while (node != null && !(node instanceof ExprParser.EventContext)) {
            node = node.getParent();
        }
        if (node == null)
            throw new RuntimeException("EventContext not found above: " + actionCtx.getText());
        for (int i = 0; i < node.getChildCount(); i++) {
            if (node.getChild(i) instanceof ExprParser.Player_refContext) {
                return (ExprParser.Player_refContext) node.getChild(i);
            }
        }
        throw new RuntimeException("player_ref not found for action: " + actionCtx.getText());
    }
}