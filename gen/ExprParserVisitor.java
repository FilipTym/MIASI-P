// Generated from C:/Users/user/IdeaProjects/GameProtocol/src/ExprParser.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ExprParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ExprParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ExprParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(ExprParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#game}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGame(ExprParser.GameContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#header}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeader(ExprParser.HeaderContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#quarter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuarter(ExprParser.QuarterContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#event}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEvent(ExprParser.EventContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#player_ref}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlayer_ref(ExprParser.Player_refContext ctx);
	/**
	 * Visit a parse tree produced by the {@code score_2pt}
	 * labeled alternative in {@link ExprParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScore_2pt(ExprParser.Score_2ptContext ctx);
	/**
	 * Visit a parse tree produced by the {@code score_3pt}
	 * labeled alternative in {@link ExprParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScore_3pt(ExprParser.Score_3ptContext ctx);
	/**
	 * Visit a parse tree produced by the {@code score_ft}
	 * labeled alternative in {@link ExprParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScore_ft(ExprParser.Score_ftContext ctx);
	/**
	 * Visit a parse tree produced by the {@code miss}
	 * labeled alternative in {@link ExprParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMiss(ExprParser.MissContext ctx);
	/**
	 * Visit a parse tree produced by the {@code reb_off}
	 * labeled alternative in {@link ExprParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReb_off(ExprParser.Reb_offContext ctx);
	/**
	 * Visit a parse tree produced by the {@code reb_def}
	 * labeled alternative in {@link ExprParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReb_def(ExprParser.Reb_defContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assist}
	 * labeled alternative in {@link ExprParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssist(ExprParser.AssistContext ctx);
	/**
	 * Visit a parse tree produced by the {@code steal}
	 * labeled alternative in {@link ExprParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSteal(ExprParser.StealContext ctx);
	/**
	 * Visit a parse tree produced by the {@code block}
	 * labeled alternative in {@link ExprParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(ExprParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code turnover}
	 * labeled alternative in {@link ExprParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTurnover(ExprParser.TurnoverContext ctx);
	/**
	 * Visit a parse tree produced by the {@code foul}
	 * labeled alternative in {@link ExprParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFoul(ExprParser.FoulContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#foul_action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFoul_action(ExprParser.Foul_actionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#boxscore_cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoxscore_cmd(ExprParser.Boxscore_cmdContext ctx);
}