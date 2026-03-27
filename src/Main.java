import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // dane testowe
        String inputStr =
                "GAME HOME vs AWAY;\n" +
                        "QUARTER 1\n" +
                        "    HOME #5 2pt;\n" +
                        "    HOME #5 reb_def;\n" +
                        "    AWAY #5 foul_personal;\n" +
                        "    if HOME #5 fouls >= 6 {\n" +
                        "        HOME #5 foul_out;\n" +
                        "    }\n" +
                        "        AWAY #23 3pt;\n" +
                        "    if HOME #23 points >= 10 and HOME #23 rebounds >= 10 {\n" +
                        "        HOME #23 double_double;\n" +
                        "    }\n" +
                        "END;\n" +
                        "BOXSCORE;\n";

        CharStream input = CharStreams.fromString(inputStr);

        //  ściezka ANTLR
        ExprLexer lexer = new ExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExprParser parser = new ExprParser(tokens);

        //  drzewo
        ParseTree tree = parser.program();

        // Uruchomienie  visitora
        Visitor visitor = new Visitor();
        visitor.visit(tree);
    }
}