package com.miasip.backend;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Main {

    public static void main(String[] args) throws Exception {

        CharStream input = CharStreams.fromString(
                // ----------------------------------------------------------------
                // RULES block: game settings + mandatory rosters for both teams.
                // Team names here must match exactly what's used in the GAME header
                // (or you can put them after the GAME header if you prefer — both orders work).
                // ----------------------------------------------------------------
                "RULES\n" +
                        "    players_on_court = 5;\n" +
                        "    quarters         = 4;\n" +
                        "    quarter_length   = 12;\n" +
                        "\n" +
                        "    ROSTER Lakers: #5, #23, #3, #11, #2;\n" +
                        "    ROSTER Celtics: #0, #11, #7, #13, #36;\n" +
                        "END;\n" +
                        "\n" +
                        // ----------------------------------------------------------------
                        // GAME header: full name + short alias.
                        // After this line both "Lakers" and "LAL" (and "Celtics"/"BOS")
                        // are valid everywhere in the quarter events below.
                        // ----------------------------------------------------------------
                        "GAME Lakers as LAL vs Celtics as BOS;\n" +
                        "\n" +
                        "QUARTER 1\n" +
                        "    LAL sub_in #5;\n" +
                        "    LAL sub_in #23;\n" +
                        "    BOS sub_in #7;\n" +
                        "    BOS sub_in #11;\n" +
                        "    LAL #5  2pt;\n" +       // alias LAL accepted
                        "    BOS #7  3pt;\n" +       // alias BOS accepted
                        "    Lakers #23 reb_def;\n" + // full name still works
                        "    Celtics #11 pf;\n" +
                        "    LAL #5  ft;\n" +
                        "    LAL #5  ft;\n" +
                        "END;\n" +
                        "\n" +
                        "QUARTER 2\n" +
                        "    LAL #23 ast;\n" +
                        "    BOS #7  stl;\n" +
                        "    BOS #7  2pt;\n" +
                        "    LAL #5  3pt;\n" +
                        "    LAL #23 blk;\n" +
                        "    BOS #7  to;\n" +
                        "END;\n" +
                        "\n" +
                        "BOXSCORE;\n"
        );

        ExprLexer lexer = new ExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExprParser parser = new ExprParser(tokens);

        ParseTree tree = parser.program();
        System.out.println("=== Parse Tree ===");
        System.out.println(tree.toStringTree(parser));
        System.out.println();

        Visitor visitor = new Visitor();
        visitor.visit(tree);

        Visitor.GameResult r = visitor.getResult();
        System.out.println("=== Aliases ===");
        r.teamAliases.forEach((alias, full) ->
                System.out.printf("  %s → %s%n", alias, full));

        System.out.println("\n=== Rosters ===");
        r.rosters.forEach((team, players) -> {
            System.out.println("  " + team + ":");
            players.forEach(p -> System.out.printf("    #%d%n", p.number));
        });

        System.out.println("\n=== Events ===");
        r.events.forEach(e -> System.out.println("  " + e));

        if (!r.errors.isEmpty()) {
            System.out.println("\n=== Errors / Warnings ===");
            r.errors.forEach(e -> System.out.println("  [!] " + e));
        }
    }
}