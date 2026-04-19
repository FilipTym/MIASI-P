import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Main {

    public static void main(String[] args) throws Exception {

        // --- Wejście ---
        CharStream input = CharStreams.fromString(
                "GAME HOME vs AWAY;\n" +
                        "\n" +
                        "QUARTER 1\n" +
                        "    HOME #5 2pt;\n" +
                        "    AWAY #23 3pt;\n" +
                        "    HOME #11 reb_def;\n" +
                        "    AWAY #7 foul_personal;\n" +
                        "    HOME #5 ft;\n" +
                        "    HOME #5 ft;\n" +
                        "END;\n" +
                        "\n" +
                        "QUARTER 2\n" +
                        "    HOME #33 ast;\n" +
                        "    AWAY #10 stl;\n" +
                        "    AWAY #10 2pt;\n" +
                        "    HOME #5 3pt;\n" +
                        "    HOME #11 blk;\n" +
                        "    AWAY #23 to;\n" +
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