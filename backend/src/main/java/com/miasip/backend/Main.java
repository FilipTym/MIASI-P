package com.miasip.backend;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Main {

    public static void main(String[] args) throws Exception {

        CharStream input = CharStreams.fromString(
                "RULES\n" +
                        "    players_on_court = 5;\n" +
                        "    quarters = 4;\n" +
                        "    quarter_length = 12;\n" +
                        "END;\n" +
                        "\n" +
                        "GAME Lakers vs Celtics;\n" +
                        "\n" +
                        "QUARTER 1\n" +
                        "    Lakers sub_in #5;\n" +
                        "    Lakers sub_in #23;\n" +
                        "    Celtics sub_in #7;\n" +
                        "    Celtics sub_in #11;\n" +
                        "    Lakers #5 2pt;\n" +
                        "    Celtics #7 3pt;\n" +
                        "    Lakers #23 reb_def;\n" +
                        "    Celtics #11 pf;\n" +
                        "    Lakers #5 ft;\n" +
                        "    Lakers #5 ft;\n" +
                        "END;\n" +
                        "\n" +
                        "QUARTER 2\n" +
                        "    Lakers #23 ast;\n" +
                        "    Celtics #7 stl;\n" +
                        "    Celtics #7 2pt;\n" +
                        "    Lakers #5 3pt;\n" +
                        "    Lakers #23 blk;\n" +
                        "    Celtics #7 to;\n" +
                        "END;\n" +
                        "\n" +
                        "BOXSCORE;\n"
        );
        // CharStream input = CharStreams.fromStream(System.in);

        // --- Lekser i parser ---
        ExprLexer lexer = new ExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExprParser parser = new ExprParser(tokens);

        // --- Drzewo parsowania ---
        ParseTree tree = parser.program();
        System.out.println("=== Parse Tree ===");
        System.out.println(tree.toStringTree(parser));
        System.out.println();

        // --- Visitor ---
        Visitor visitor = new Visitor();
        visitor.visit(tree);
    }
}