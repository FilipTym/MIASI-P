package com.miasip.backend;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*") // Allow frontend to call this
public class BoxscoreController {

    public static class ParseRequest {
        public String code;
    }

    @PostMapping("/api/parse")
    public Visitor.GameResult parse(@RequestBody ParseRequest request) {
        return runParser(request.code);
    }

    @PostMapping("/api/validate")
    public Visitor.GameResult validate(@RequestBody ParseRequest request) {
        return runParser(request.code);
    }

    private Visitor.GameResult runParser(String code) {
        try {
            CharStream input = CharStreams.fromString(code);
            ExprLexer lexer = new ExprLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ExprParser parser = new ExprParser(tokens);

            ParseTree tree = parser.program();

            Visitor visitor = new Visitor();
            visitor.visit(tree);

            return visitor.getResult();
        } catch (Exception e) {
            Visitor.GameResult errorResult = new Visitor.GameResult();
            errorResult.errors.add("Parse error: " + e.getMessage());
            return errorResult;
        }
    }
}