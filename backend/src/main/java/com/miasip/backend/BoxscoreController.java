package com.miasip.backend;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*") // Allow frontend to call this
public class BoxscoreController {

    public static class ParseRequest {
        public String code;
    }

    @PostMapping("/api/parse")
    public Visitor.GameResult parse(@RequestBody ParseRequest request) {
        try {
            CharStream input = CharStreams.fromString(request.code);
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