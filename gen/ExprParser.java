// Generated from C:/Users/user/Documents/GitHub/MIASI-P/src/ExprParser.g4 by ANTLR 4.13.2
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
		FOUL_P=18, FOUL_T=19, FOUL_F=20, FOUL_OUT=21, DOUBLE_DOUBLE=22, TRIPLE_DOUBLE=23, 
		STAT_PTS=24, STAT_REB=25, STAT_AST=26, STAT_STL=27, STAT_BLK=28, STAT_FLS=29, 
		STAT_TO=30, IF=31, ELSE=32, AND=33, GTE=34, HASH=35, SEMI=36, LCURLY=37, 
		RCURLY=38, INT=39, ID=40, WS=41, BOXSCORE=42;
	public static final int
		RULE_program = 0, RULE_game = 1, RULE_header = 2, RULE_quarter = 3, RULE_event = 4, 
		RULE_if_stat = 5, RULE_condition = 6, RULE_stat_name = 7, RULE_player_ref = 8, 
		RULE_action = 9, RULE_foul_action = 10, RULE_boxscore_cmd = 11;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "game", "header", "quarter", "event", "if_stat", "condition", 
			"stat_name", "player_ref", "action", "foul_action", "boxscore_cmd"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'GAME'", "'vs'", "'QUARTER'", "'OT'", "'END'", "'HOME'", "'AWAY'", 
			"'2pt'", "'3pt'", "'ft'", "'miss'", "'reb_off'", "'reb_def'", "'ast'", 
			"'stl'", "'blk'", "'to'", "'foul_personal'", "'foul_technical'", "'foul_flagrant'", 
			"'foul_out'", "'double_double'", "'triple_double'", "'points'", "'rebounds'", 
			"'assists'", "'steals'", "'blocks'", "'fouls'", "'turnovers'", "'if'", 
			"'else'", "'and'", "'>='", "'#'", "';'", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "GAME", "VS", "QUARTER", "OT", "END", "HOME", "AWAY", "TWO_PT", 
			"THREE_PT", "FT", "MISS", "REB_OFF", "REB_DEF", "AST", "STL", "BLK", 
			"TO", "FOUL_P", "FOUL_T", "FOUL_F", "FOUL_OUT", "DOUBLE_DOUBLE", "TRIPLE_DOUBLE", 
			"STAT_PTS", "STAT_REB", "STAT_AST", "STAT_STL", "STAT_BLK", "STAT_FLS", 
			"STAT_TO", "IF", "ELSE", "AND", "GTE", "HASH", "SEMI", "LCURLY", "RCURLY", 
			"INT", "ID", "WS", "BOXSCORE"
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
			setState(25); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(24);
				game();
				}
				}
				setState(27); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==GAME );
			setState(30);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BOXSCORE) {
				{
				setState(29);
				boxscore_cmd();
				}
			}

			setState(32);
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
			setState(34);
			header();
			setState(36); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(35);
				quarter();
				}
				}
				setState(38); 
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
			setState(40);
			match(GAME);
			setState(41);
			match(HOME);
			setState(42);
			match(VS);
			setState(43);
			match(AWAY);
			setState(44);
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
		public List<If_statContext> if_stat() {
			return getRuleContexts(If_statContext.class);
		}
		public If_statContext if_stat(int i) {
			return getRuleContext(If_statContext.class,i);
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
			setState(46);
			match(QUARTER);
			setState(47);
			_la = _input.LA(1);
			if ( !(_la==OT || _la==INT) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(52);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2147483840L) != 0)) {
				{
				setState(50);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case HOME:
				case AWAY:
					{
					setState(48);
					event();
					}
					break;
				case IF:
					{
					setState(49);
					if_stat();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(54);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(55);
			match(END);
			setState(56);
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
			setState(58);
			player_ref();
			setState(59);
			action();
			setState(60);
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
	public static class If_statContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(ExprParser.IF, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public List<TerminalNode> LCURLY() { return getTokens(ExprParser.LCURLY); }
		public TerminalNode LCURLY(int i) {
			return getToken(ExprParser.LCURLY, i);
		}
		public List<TerminalNode> RCURLY() { return getTokens(ExprParser.RCURLY); }
		public TerminalNode RCURLY(int i) {
			return getToken(ExprParser.RCURLY, i);
		}
		public List<EventContext> event() {
			return getRuleContexts(EventContext.class);
		}
		public EventContext event(int i) {
			return getRuleContext(EventContext.class,i);
		}
		public List<If_statContext> if_stat() {
			return getRuleContexts(If_statContext.class);
		}
		public If_statContext if_stat(int i) {
			return getRuleContext(If_statContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(ExprParser.ELSE, 0); }
		public If_statContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_stat; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprParserVisitor ) return ((ExprParserVisitor<? extends T>)visitor).visitIf_stat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final If_statContext if_stat() throws RecognitionException {
		If_statContext _localctx = new If_statContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_if_stat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			match(IF);
			setState(63);
			condition();
			setState(64);
			match(LCURLY);
			setState(69);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2147483840L) != 0)) {
				{
				setState(67);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case HOME:
				case AWAY:
					{
					setState(65);
					event();
					}
					break;
				case IF:
					{
					setState(66);
					if_stat();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(71);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(72);
			match(RCURLY);
			setState(83);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(73);
				match(ELSE);
				setState(74);
				match(LCURLY);
				setState(79);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2147483840L) != 0)) {
					{
					setState(77);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case HOME:
					case AWAY:
						{
						setState(75);
						event();
						}
						break;
					case IF:
						{
						setState(76);
						if_stat();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(81);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(82);
				match(RCURLY);
				}
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
	public static class ConditionContext extends ParserRuleContext {
		public List<Player_refContext> player_ref() {
			return getRuleContexts(Player_refContext.class);
		}
		public Player_refContext player_ref(int i) {
			return getRuleContext(Player_refContext.class,i);
		}
		public List<Stat_nameContext> stat_name() {
			return getRuleContexts(Stat_nameContext.class);
		}
		public Stat_nameContext stat_name(int i) {
			return getRuleContext(Stat_nameContext.class,i);
		}
		public List<TerminalNode> GTE() { return getTokens(ExprParser.GTE); }
		public TerminalNode GTE(int i) {
			return getToken(ExprParser.GTE, i);
		}
		public List<TerminalNode> INT() { return getTokens(ExprParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(ExprParser.INT, i);
		}
		public List<TerminalNode> AND() { return getTokens(ExprParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(ExprParser.AND, i);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprParserVisitor ) return ((ExprParserVisitor<? extends T>)visitor).visitCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_condition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			player_ref();
			setState(86);
			stat_name();
			setState(87);
			match(GTE);
			setState(88);
			match(INT);
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(89);
				match(AND);
				setState(90);
				player_ref();
				setState(91);
				stat_name();
				setState(92);
				match(GTE);
				setState(93);
				match(INT);
				}
				}
				setState(99);
				_errHandler.sync(this);
				_la = _input.LA(1);
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
	public static class Stat_nameContext extends ParserRuleContext {
		public TerminalNode STAT_PTS() { return getToken(ExprParser.STAT_PTS, 0); }
		public TerminalNode STAT_REB() { return getToken(ExprParser.STAT_REB, 0); }
		public TerminalNode STAT_AST() { return getToken(ExprParser.STAT_AST, 0); }
		public TerminalNode STAT_STL() { return getToken(ExprParser.STAT_STL, 0); }
		public TerminalNode STAT_BLK() { return getToken(ExprParser.STAT_BLK, 0); }
		public TerminalNode STAT_FLS() { return getToken(ExprParser.STAT_FLS, 0); }
		public TerminalNode STAT_TO() { return getToken(ExprParser.STAT_TO, 0); }
		public Stat_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat_name; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprParserVisitor ) return ((ExprParserVisitor<? extends T>)visitor).visitStat_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Stat_nameContext stat_name() throws RecognitionException {
		Stat_nameContext _localctx = new Stat_nameContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_stat_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 2130706432L) != 0)) ) {
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
		enterRule(_localctx, 16, RULE_player_ref);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			_la = _input.LA(1);
			if ( !(_la==HOME || _la==AWAY) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(103);
			match(HASH);
			setState(104);
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
	public static class Double_doubleContext extends ActionContext {
		public TerminalNode DOUBLE_DOUBLE() { return getToken(ExprParser.DOUBLE_DOUBLE, 0); }
		public Double_doubleContext(ActionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprParserVisitor ) return ((ExprParserVisitor<? extends T>)visitor).visitDouble_double(this);
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
	public static class Triple_doubleContext extends ActionContext {
		public TerminalNode TRIPLE_DOUBLE() { return getToken(ExprParser.TRIPLE_DOUBLE, 0); }
		public Triple_doubleContext(ActionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprParserVisitor ) return ((ExprParserVisitor<? extends T>)visitor).visitTriple_double(this);
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
	public static class Foul_outContext extends ActionContext {
		public TerminalNode FOUL_OUT() { return getToken(ExprParser.FOUL_OUT, 0); }
		public Foul_outContext(ActionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprParserVisitor ) return ((ExprParserVisitor<? extends T>)visitor).visitFoul_out(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActionContext action() throws RecognitionException {
		ActionContext _localctx = new ActionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_action);
		try {
			setState(120);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TWO_PT:
				_localctx = new Score_2ptContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(106);
				match(TWO_PT);
				}
				break;
			case THREE_PT:
				_localctx = new Score_3ptContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(107);
				match(THREE_PT);
				}
				break;
			case FT:
				_localctx = new Score_ftContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(108);
				match(FT);
				}
				break;
			case MISS:
				_localctx = new MissContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(109);
				match(MISS);
				}
				break;
			case REB_OFF:
				_localctx = new Reb_offContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(110);
				match(REB_OFF);
				}
				break;
			case REB_DEF:
				_localctx = new Reb_defContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(111);
				match(REB_DEF);
				}
				break;
			case AST:
				_localctx = new AssistContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(112);
				match(AST);
				}
				break;
			case STL:
				_localctx = new StealContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(113);
				match(STL);
				}
				break;
			case BLK:
				_localctx = new BlockContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(114);
				match(BLK);
				}
				break;
			case TO:
				_localctx = new TurnoverContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(115);
				match(TO);
				}
				break;
			case FOUL_P:
			case FOUL_T:
			case FOUL_F:
				_localctx = new FoulContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(116);
				foul_action();
				}
				break;
			case DOUBLE_DOUBLE:
				_localctx = new Double_doubleContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(117);
				match(DOUBLE_DOUBLE);
				}
				break;
			case TRIPLE_DOUBLE:
				_localctx = new Triple_doubleContext(_localctx);
				enterOuterAlt(_localctx, 13);
				{
				setState(118);
				match(TRIPLE_DOUBLE);
				}
				break;
			case FOUL_OUT:
				_localctx = new Foul_outContext(_localctx);
				enterOuterAlt(_localctx, 14);
				{
				setState(119);
				match(FOUL_OUT);
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
		enterRule(_localctx, 20, RULE_foul_action);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
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
		public TerminalNode BOXSCORE() { return getToken(ExprParser.BOXSCORE, 0); }
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
		enterRule(_localctx, 22, RULE_boxscore_cmd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			match(BOXSCORE);
			setState(125);
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

	public static final String _serializedATN =
		"\u0004\u0001*\u0080\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0001"+
		"\u0000\u0004\u0000\u001a\b\u0000\u000b\u0000\f\u0000\u001b\u0001\u0000"+
		"\u0003\u0000\u001f\b\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0004\u0001%\b\u0001\u000b\u0001\f\u0001&\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0005\u00033\b\u0003\n\u0003\f\u00036\t\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005"+
		"\u0005D\b\u0005\n\u0005\f\u0005G\t\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0005\u0005N\b\u0005\n\u0005\f\u0005Q\t"+
		"\u0005\u0001\u0005\u0003\u0005T\b\u0005\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0005\u0006`\b\u0006\n\u0006\f\u0006c\t\u0006\u0001"+
		"\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0003\ty\b\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0000\u0000\f\u0000\u0002\u0004\u0006\b\n\f\u000e"+
		"\u0010\u0012\u0014\u0016\u0000\u0004\u0002\u0000\u0004\u0004\'\'\u0001"+
		"\u0000\u0018\u001e\u0001\u0000\u0006\u0007\u0001\u0000\u0012\u0014\u008b"+
		"\u0000\u0019\u0001\u0000\u0000\u0000\u0002\"\u0001\u0000\u0000\u0000\u0004"+
		"(\u0001\u0000\u0000\u0000\u0006.\u0001\u0000\u0000\u0000\b:\u0001\u0000"+
		"\u0000\u0000\n>\u0001\u0000\u0000\u0000\fU\u0001\u0000\u0000\u0000\u000e"+
		"d\u0001\u0000\u0000\u0000\u0010f\u0001\u0000\u0000\u0000\u0012x\u0001"+
		"\u0000\u0000\u0000\u0014z\u0001\u0000\u0000\u0000\u0016|\u0001\u0000\u0000"+
		"\u0000\u0018\u001a\u0003\u0002\u0001\u0000\u0019\u0018\u0001\u0000\u0000"+
		"\u0000\u001a\u001b\u0001\u0000\u0000\u0000\u001b\u0019\u0001\u0000\u0000"+
		"\u0000\u001b\u001c\u0001\u0000\u0000\u0000\u001c\u001e\u0001\u0000\u0000"+
		"\u0000\u001d\u001f\u0003\u0016\u000b\u0000\u001e\u001d\u0001\u0000\u0000"+
		"\u0000\u001e\u001f\u0001\u0000\u0000\u0000\u001f \u0001\u0000\u0000\u0000"+
		" !\u0005\u0000\u0000\u0001!\u0001\u0001\u0000\u0000\u0000\"$\u0003\u0004"+
		"\u0002\u0000#%\u0003\u0006\u0003\u0000$#\u0001\u0000\u0000\u0000%&\u0001"+
		"\u0000\u0000\u0000&$\u0001\u0000\u0000\u0000&\'\u0001\u0000\u0000\u0000"+
		"\'\u0003\u0001\u0000\u0000\u0000()\u0005\u0001\u0000\u0000)*\u0005\u0006"+
		"\u0000\u0000*+\u0005\u0002\u0000\u0000+,\u0005\u0007\u0000\u0000,-\u0005"+
		"$\u0000\u0000-\u0005\u0001\u0000\u0000\u0000./\u0005\u0003\u0000\u0000"+
		"/4\u0007\u0000\u0000\u000003\u0003\b\u0004\u000013\u0003\n\u0005\u0000"+
		"20\u0001\u0000\u0000\u000021\u0001\u0000\u0000\u000036\u0001\u0000\u0000"+
		"\u000042\u0001\u0000\u0000\u000045\u0001\u0000\u0000\u000057\u0001\u0000"+
		"\u0000\u000064\u0001\u0000\u0000\u000078\u0005\u0005\u0000\u000089\u0005"+
		"$\u0000\u00009\u0007\u0001\u0000\u0000\u0000:;\u0003\u0010\b\u0000;<\u0003"+
		"\u0012\t\u0000<=\u0005$\u0000\u0000=\t\u0001\u0000\u0000\u0000>?\u0005"+
		"\u001f\u0000\u0000?@\u0003\f\u0006\u0000@E\u0005%\u0000\u0000AD\u0003"+
		"\b\u0004\u0000BD\u0003\n\u0005\u0000CA\u0001\u0000\u0000\u0000CB\u0001"+
		"\u0000\u0000\u0000DG\u0001\u0000\u0000\u0000EC\u0001\u0000\u0000\u0000"+
		"EF\u0001\u0000\u0000\u0000FH\u0001\u0000\u0000\u0000GE\u0001\u0000\u0000"+
		"\u0000HS\u0005&\u0000\u0000IJ\u0005 \u0000\u0000JO\u0005%\u0000\u0000"+
		"KN\u0003\b\u0004\u0000LN\u0003\n\u0005\u0000MK\u0001\u0000\u0000\u0000"+
		"ML\u0001\u0000\u0000\u0000NQ\u0001\u0000\u0000\u0000OM\u0001\u0000\u0000"+
		"\u0000OP\u0001\u0000\u0000\u0000PR\u0001\u0000\u0000\u0000QO\u0001\u0000"+
		"\u0000\u0000RT\u0005&\u0000\u0000SI\u0001\u0000\u0000\u0000ST\u0001\u0000"+
		"\u0000\u0000T\u000b\u0001\u0000\u0000\u0000UV\u0003\u0010\b\u0000VW\u0003"+
		"\u000e\u0007\u0000WX\u0005\"\u0000\u0000Xa\u0005\'\u0000\u0000YZ\u0005"+
		"!\u0000\u0000Z[\u0003\u0010\b\u0000[\\\u0003\u000e\u0007\u0000\\]\u0005"+
		"\"\u0000\u0000]^\u0005\'\u0000\u0000^`\u0001\u0000\u0000\u0000_Y\u0001"+
		"\u0000\u0000\u0000`c\u0001\u0000\u0000\u0000a_\u0001\u0000\u0000\u0000"+
		"ab\u0001\u0000\u0000\u0000b\r\u0001\u0000\u0000\u0000ca\u0001\u0000\u0000"+
		"\u0000de\u0007\u0001\u0000\u0000e\u000f\u0001\u0000\u0000\u0000fg\u0007"+
		"\u0002\u0000\u0000gh\u0005#\u0000\u0000hi\u0005\'\u0000\u0000i\u0011\u0001"+
		"\u0000\u0000\u0000jy\u0005\b\u0000\u0000ky\u0005\t\u0000\u0000ly\u0005"+
		"\n\u0000\u0000my\u0005\u000b\u0000\u0000ny\u0005\f\u0000\u0000oy\u0005"+
		"\r\u0000\u0000py\u0005\u000e\u0000\u0000qy\u0005\u000f\u0000\u0000ry\u0005"+
		"\u0010\u0000\u0000sy\u0005\u0011\u0000\u0000ty\u0003\u0014\n\u0000uy\u0005"+
		"\u0016\u0000\u0000vy\u0005\u0017\u0000\u0000wy\u0005\u0015\u0000\u0000"+
		"xj\u0001\u0000\u0000\u0000xk\u0001\u0000\u0000\u0000xl\u0001\u0000\u0000"+
		"\u0000xm\u0001\u0000\u0000\u0000xn\u0001\u0000\u0000\u0000xo\u0001\u0000"+
		"\u0000\u0000xp\u0001\u0000\u0000\u0000xq\u0001\u0000\u0000\u0000xr\u0001"+
		"\u0000\u0000\u0000xs\u0001\u0000\u0000\u0000xt\u0001\u0000\u0000\u0000"+
		"xu\u0001\u0000\u0000\u0000xv\u0001\u0000\u0000\u0000xw\u0001\u0000\u0000"+
		"\u0000y\u0013\u0001\u0000\u0000\u0000z{\u0007\u0003\u0000\u0000{\u0015"+
		"\u0001\u0000\u0000\u0000|}\u0005*\u0000\u0000}~\u0005$\u0000\u0000~\u0017"+
		"\u0001\u0000\u0000\u0000\f\u001b\u001e&24CEMOSax";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}