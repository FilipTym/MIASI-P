// Generated from C:/Users/user/IdeaProjects/GameProtocol/src/ExprParser.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class ExprParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		GAME=1, VS=2, QUARTER=3, OT=4, END=5, HOME=6, AWAY=7, TWO_PT=8, THREE_PT=9, 
		FT=10, MISS=11, REB_OFF=12, REB_DEF=13, AST=14, STL=15, BLK=16, TO=17, 
		FOUL_P=18, FOUL_T=19, FOUL_F=20, BOXSCORE=21, HASH=22, SEMI=23, INT=24, 
		ID=25, WS=26;
	public static final int
		RULE_program = 0, RULE_game = 1, RULE_header = 2, RULE_quarter = 3, RULE_event = 4, 
		RULE_player_ref = 5, RULE_action = 6, RULE_foul_action = 7, RULE_boxscore_cmd = 8;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "game", "header", "quarter", "event", "player_ref", "action", 
			"foul_action", "boxscore_cmd"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'GAME'", "'vs'", "'QUARTER'", "'OT'", "'END'", "'HOME'", "'AWAY'", 
			"'2pt'", "'3pt'", "'ft'", "'miss'", "'reb_off'", "'reb_def'", "'ast'", 
			"'stl'", "'blk'", "'to'", "'foul_personal'", "'foul_technical'", "'foul_flagrant'", 
			"'BOXSCORE'", "'#'", "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "GAME", "VS", "QUARTER", "OT", "END", "HOME", "AWAY", "TWO_PT", 
			"THREE_PT", "FT", "MISS", "REB_OFF", "REB_DEF", "AST", "STL", "BLK", 
			"TO", "FOUL_P", "FOUL_T", "FOUL_F", "BOXSCORE", "HASH", "SEMI", "INT", 
			"ID", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "ExprParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ExprParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(ExprParser.EOF, 0); }
		public List<GameContext> game() {
			return getRuleContexts(GameContext.class);
		}
		public GameContext game(int i) {
			return getRuleContext(GameContext.class,i);
		}
		public Boxscore_cmdContext boxscore_cmd() {
			return getRuleContext(Boxscore_cmdContext.class,0);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprParserVisitor ) return ((ExprParserVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(19); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(18);
				game();
				}
				}
				setState(21); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==GAME );
			setState(24);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BOXSCORE) {
				{
				setState(23);
				boxscore_cmd();
				}
			}

			setState(26);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GameContext extends ParserRuleContext {
		public HeaderContext header() {
			return getRuleContext(HeaderContext.class,0);
		}
		public List<QuarterContext> quarter() {
			return getRuleContexts(QuarterContext.class);
		}
		public QuarterContext quarter(int i) {
			return getRuleContext(QuarterContext.class,i);
		}
		public GameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_game; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprParserVisitor ) return ((ExprParserVisitor<? extends T>)visitor).visitGame(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GameContext game() throws RecognitionException {
		GameContext _localctx = new GameContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_game);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			header();
			setState(30); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(29);
				quarter();
				}
				}
				setState(32); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==QUARTER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class HeaderContext extends ParserRuleContext {
		public TerminalNode GAME() { return getToken(ExprParser.GAME, 0); }
		public TerminalNode HOME() { return getToken(ExprParser.HOME, 0); }
		public TerminalNode VS() { return getToken(ExprParser.VS, 0); }
		public TerminalNode AWAY() { return getToken(ExprParser.AWAY, 0); }
		public TerminalNode SEMI() { return getToken(ExprParser.SEMI, 0); }
		public HeaderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_header; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprParserVisitor ) return ((ExprParserVisitor<? extends T>)visitor).visitHeader(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HeaderContext header() throws RecognitionException {
		HeaderContext _localctx = new HeaderContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_header);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			match(GAME);
			setState(35);
			match(HOME);
			setState(36);
			match(VS);
			setState(37);
			match(AWAY);
			setState(38);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QuarterContext extends ParserRuleContext {
		public TerminalNode QUARTER() { return getToken(ExprParser.QUARTER, 0); }
		public TerminalNode END() { return getToken(ExprParser.END, 0); }
		public TerminalNode SEMI() { return getToken(ExprParser.SEMI, 0); }
		public TerminalNode INT() { return getToken(ExprParser.INT, 0); }
		public TerminalNode OT() { return getToken(ExprParser.OT, 0); }
		public List<EventContext> event() {
			return getRuleContexts(EventContext.class);
		}
		public EventContext event(int i) {
			return getRuleContext(EventContext.class,i);
		}
		public QuarterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quarter; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprParserVisitor ) return ((ExprParserVisitor<? extends T>)visitor).visitQuarter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuarterContext quarter() throws RecognitionException {
		QuarterContext _localctx = new QuarterContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_quarter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			match(QUARTER);
			setState(41);
			_la = _input.LA(1);
			if ( !(_la==OT || _la==INT) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(45);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==HOME || _la==AWAY) {
				{
				{
				setState(42);
				event();
				}
				}
				setState(47);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(48);
			match(END);
			setState(49);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EventContext extends ParserRuleContext {
		public Player_refContext player_ref() {
			return getRuleContext(Player_refContext.class,0);
		}
		public ActionContext action() {
			return getRuleContext(ActionContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(ExprParser.SEMI, 0); }
		public EventContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_event; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprParserVisitor ) return ((ExprParserVisitor<? extends T>)visitor).visitEvent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EventContext event() throws RecognitionException {
		EventContext _localctx = new EventContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_event);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			player_ref();
			setState(52);
			action();
			setState(53);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Player_refContext extends ParserRuleContext {
		public TerminalNode HASH() { return getToken(ExprParser.HASH, 0); }
		public TerminalNode INT() { return getToken(ExprParser.INT, 0); }
		public TerminalNode HOME() { return getToken(ExprParser.HOME, 0); }
		public TerminalNode AWAY() { return getToken(ExprParser.AWAY, 0); }
		public Player_refContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_player_ref; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprParserVisitor ) return ((ExprParserVisitor<? extends T>)visitor).visitPlayer_ref(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Player_refContext player_ref() throws RecognitionException {
		Player_refContext _localctx = new Player_refContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_player_ref);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			_la = _input.LA(1);
			if ( !(_la==HOME || _la==AWAY) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(56);
			match(HASH);
			setState(57);
			match(INT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ActionContext extends ParserRuleContext {
		public ActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_action; }
	 
		public ActionContext() { }
		public void copyFrom(ActionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class Score_2ptContext extends ActionContext {
		public TerminalNode TWO_PT() { return getToken(ExprParser.TWO_PT, 0); }
		public Score_2ptContext(ActionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprParserVisitor ) return ((ExprParserVisitor<? extends T>)visitor).visitScore_2pt(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FoulContext extends ActionContext {
		public Foul_actionContext foul_action() {
			return getRuleContext(Foul_actionContext.class,0);
		}
		public FoulContext(ActionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprParserVisitor ) return ((ExprParserVisitor<? extends T>)visitor).visitFoul(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class Score_3ptContext extends ActionContext {
		public TerminalNode THREE_PT() { return getToken(ExprParser.THREE_PT, 0); }
		public Score_3ptContext(ActionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprParserVisitor ) return ((ExprParserVisitor<? extends T>)visitor).visitScore_3pt(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class Score_ftContext extends ActionContext {
		public TerminalNode FT() { return getToken(ExprParser.FT, 0); }
		public Score_ftContext(ActionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprParserVisitor ) return ((ExprParserVisitor<? extends T>)visitor).visitScore_ft(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class Reb_defContext extends ActionContext {
		public TerminalNode REB_DEF() { return getToken(ExprParser.REB_DEF, 0); }
		public Reb_defContext(ActionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprParserVisitor ) return ((ExprParserVisitor<? extends T>)visitor).visitReb_def(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StealContext extends ActionContext {
		public TerminalNode STL() { return getToken(ExprParser.STL, 0); }
		public StealContext(ActionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprParserVisitor ) return ((ExprParserVisitor<? extends T>)visitor).visitSteal(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AssistContext extends ActionContext {
		public TerminalNode AST() { return getToken(ExprParser.AST, 0); }
		public AssistContext(ActionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprParserVisitor ) return ((ExprParserVisitor<? extends T>)visitor).visitAssist(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BlockContext extends ActionContext {
		public TerminalNode BLK() { return getToken(ExprParser.BLK, 0); }
		public BlockContext(ActionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprParserVisitor ) return ((ExprParserVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TurnoverContext extends ActionContext {
		public TerminalNode TO() { return getToken(ExprParser.TO, 0); }
		public TurnoverContext(ActionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprParserVisitor ) return ((ExprParserVisitor<? extends T>)visitor).visitTurnover(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class Reb_offContext extends ActionContext {
		public TerminalNode REB_OFF() { return getToken(ExprParser.REB_OFF, 0); }
		public Reb_offContext(ActionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprParserVisitor ) return ((ExprParserVisitor<? extends T>)visitor).visitReb_off(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MissContext extends ActionContext {
		public TerminalNode MISS() { return getToken(ExprParser.MISS, 0); }
		public MissContext(ActionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprParserVisitor ) return ((ExprParserVisitor<? extends T>)visitor).visitMiss(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActionContext action() throws RecognitionException {
		ActionContext _localctx = new ActionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_action);
		try {
			setState(70);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TWO_PT:
				_localctx = new Score_2ptContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(59);
				match(TWO_PT);
				}
				break;
			case THREE_PT:
				_localctx = new Score_3ptContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(60);
				match(THREE_PT);
				}
				break;
			case FT:
				_localctx = new Score_ftContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(61);
				match(FT);
				}
				break;
			case MISS:
				_localctx = new MissContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(62);
				match(MISS);
				}
				break;
			case REB_OFF:
				_localctx = new Reb_offContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(63);
				match(REB_OFF);
				}
				break;
			case REB_DEF:
				_localctx = new Reb_defContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(64);
				match(REB_DEF);
				}
				break;
			case AST:
				_localctx = new AssistContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(65);
				match(AST);
				}
				break;
			case STL:
				_localctx = new StealContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(66);
				match(STL);
				}
				break;
			case BLK:
				_localctx = new BlockContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(67);
				match(BLK);
				}
				break;
			case TO:
				_localctx = new TurnoverContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(68);
				match(TO);
				}
				break;
			case FOUL_P:
			case FOUL_T:
			case FOUL_F:
				_localctx = new FoulContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(69);
				foul_action();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Foul_actionContext extends ParserRuleContext {
		public TerminalNode FOUL_P() { return getToken(ExprParser.FOUL_P, 0); }
		public TerminalNode FOUL_T() { return getToken(ExprParser.FOUL_T, 0); }
		public TerminalNode FOUL_F() { return getToken(ExprParser.FOUL_F, 0); }
		public Foul_actionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_foul_action; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprParserVisitor ) return ((ExprParserVisitor<? extends T>)visitor).visitFoul_action(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Foul_actionContext foul_action() throws RecognitionException {
		Foul_actionContext _localctx = new Foul_actionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_foul_action);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1835008L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Boxscore_cmdContext extends ParserRuleContext {
		public List<TerminalNode> BOXSCORE() { return getTokens(ExprParser.BOXSCORE); }
		public TerminalNode BOXSCORE(int i) {
			return getToken(ExprParser.BOXSCORE, i);
		}
		public TerminalNode SEMI() { return getToken(ExprParser.SEMI, 0); }
		public Boxscore_cmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boxscore_cmd; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprParserVisitor ) return ((ExprParserVisitor<? extends T>)visitor).visitBoxscore_cmd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Boxscore_cmdContext boxscore_cmd() throws RecognitionException {
		Boxscore_cmdContext _localctx = new Boxscore_cmdContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_boxscore_cmd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			match(BOXSCORE);
			setState(75);
			match(SEMI);
			setState(76);
			match(BOXSCORE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u001aO\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0001\u0000\u0004\u0000\u0014\b\u0000\u000b\u0000\f\u0000\u0015"+
		"\u0001\u0000\u0003\u0000\u0019\b\u0000\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0004\u0001\u001f\b\u0001\u000b\u0001\f\u0001 \u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0005\u0003,\b\u0003\n\u0003\f\u0003/\t\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006G\b\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0000"+
		"\u0000\t\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0000\u0003\u0002\u0000"+
		"\u0004\u0004\u0018\u0018\u0001\u0000\u0006\u0007\u0001\u0000\u0012\u0014"+
		"S\u0000\u0013\u0001\u0000\u0000\u0000\u0002\u001c\u0001\u0000\u0000\u0000"+
		"\u0004\"\u0001\u0000\u0000\u0000\u0006(\u0001\u0000\u0000\u0000\b3\u0001"+
		"\u0000\u0000\u0000\n7\u0001\u0000\u0000\u0000\fF\u0001\u0000\u0000\u0000"+
		"\u000eH\u0001\u0000\u0000\u0000\u0010J\u0001\u0000\u0000\u0000\u0012\u0014"+
		"\u0003\u0002\u0001\u0000\u0013\u0012\u0001\u0000\u0000\u0000\u0014\u0015"+
		"\u0001\u0000\u0000\u0000\u0015\u0013\u0001\u0000\u0000\u0000\u0015\u0016"+
		"\u0001\u0000\u0000\u0000\u0016\u0018\u0001\u0000\u0000\u0000\u0017\u0019"+
		"\u0003\u0010\b\u0000\u0018\u0017\u0001\u0000\u0000\u0000\u0018\u0019\u0001"+
		"\u0000\u0000\u0000\u0019\u001a\u0001\u0000\u0000\u0000\u001a\u001b\u0005"+
		"\u0000\u0000\u0001\u001b\u0001\u0001\u0000\u0000\u0000\u001c\u001e\u0003"+
		"\u0004\u0002\u0000\u001d\u001f\u0003\u0006\u0003\u0000\u001e\u001d\u0001"+
		"\u0000\u0000\u0000\u001f \u0001\u0000\u0000\u0000 \u001e\u0001\u0000\u0000"+
		"\u0000 !\u0001\u0000\u0000\u0000!\u0003\u0001\u0000\u0000\u0000\"#\u0005"+
		"\u0001\u0000\u0000#$\u0005\u0006\u0000\u0000$%\u0005\u0002\u0000\u0000"+
		"%&\u0005\u0007\u0000\u0000&\'\u0005\u0017\u0000\u0000\'\u0005\u0001\u0000"+
		"\u0000\u0000()\u0005\u0003\u0000\u0000)-\u0007\u0000\u0000\u0000*,\u0003"+
		"\b\u0004\u0000+*\u0001\u0000\u0000\u0000,/\u0001\u0000\u0000\u0000-+\u0001"+
		"\u0000\u0000\u0000-.\u0001\u0000\u0000\u0000.0\u0001\u0000\u0000\u0000"+
		"/-\u0001\u0000\u0000\u000001\u0005\u0005\u0000\u000012\u0005\u0017\u0000"+
		"\u00002\u0007\u0001\u0000\u0000\u000034\u0003\n\u0005\u000045\u0003\f"+
		"\u0006\u000056\u0005\u0017\u0000\u00006\t\u0001\u0000\u0000\u000078\u0007"+
		"\u0001\u0000\u000089\u0005\u0016\u0000\u00009:\u0005\u0018\u0000\u0000"+
		":\u000b\u0001\u0000\u0000\u0000;G\u0005\b\u0000\u0000<G\u0005\t\u0000"+
		"\u0000=G\u0005\n\u0000\u0000>G\u0005\u000b\u0000\u0000?G\u0005\f\u0000"+
		"\u0000@G\u0005\r\u0000\u0000AG\u0005\u000e\u0000\u0000BG\u0005\u000f\u0000"+
		"\u0000CG\u0005\u0010\u0000\u0000DG\u0005\u0011\u0000\u0000EG\u0003\u000e"+
		"\u0007\u0000F;\u0001\u0000\u0000\u0000F<\u0001\u0000\u0000\u0000F=\u0001"+
		"\u0000\u0000\u0000F>\u0001\u0000\u0000\u0000F?\u0001\u0000\u0000\u0000"+
		"F@\u0001\u0000\u0000\u0000FA\u0001\u0000\u0000\u0000FB\u0001\u0000\u0000"+
		"\u0000FC\u0001\u0000\u0000\u0000FD\u0001\u0000\u0000\u0000FE\u0001\u0000"+
		"\u0000\u0000G\r\u0001\u0000\u0000\u0000HI\u0007\u0002\u0000\u0000I\u000f"+
		"\u0001\u0000\u0000\u0000JK\u0005\u0015\u0000\u0000KL\u0005\u0017\u0000"+
		"\u0000LM\u0005\u0015\u0000\u0000M\u0011\u0001\u0000\u0000\u0000\u0005"+
		"\u0015\u0018 -F";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}