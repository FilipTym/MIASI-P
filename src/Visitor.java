import org.antlr.v4.runtime.tree.ParseTree;

public class Visitor extends ExprParserBaseVisitor<Void> {

    @Override
    public Void visitHeader(ExprParser.HeaderContext ctx) {
        System.out.println(">>> Rozpoczęto mecz: HOME vs AWAY");
        return null;
    }

    @Override
    public Void visitQuarter(ExprParser.QuarterContext ctx) {
        String label = ctx.OT() != null ? "Dogrywka (OT)" : "Kwarta " + ctx.INT().getText();
        System.out.println("\n[ " + label + " ]");
        return visitChildren(ctx);
    }

    @Override
    public Void visitEvent(ExprParser.EventContext ctx) {
        // Wyciągamy informacje o graczu
        String team = ctx.player_ref().HOME() != null ? "HOME" : "AWAY";
        String playerNum = ctx.player_ref().INT().getText();

        // Pobieranie tekstu akcji
        String action = ctx.action().getText();

        System.out.printf("Zdarzenie: Drużyna %s, Gracz #%s -> Akcja: %s%n", team, playerNum, action);
        return null;
    }

    @Override
    public Void visitIf_stat(ExprParser.If_statContext ctx) {
        System.out.println("[ Sprawdzanie warunku ]");
        return visitChildren(ctx);
    }

    @Override
    public Void visitBoxscore_cmd(ExprParser.Boxscore_cmdContext ctx) {
        System.out.println("\n>>> Napotkano polecenie BOXSCORE");
        return null;
    }
}