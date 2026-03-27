lexer grammar ExprLexer;

// --- Keywords: structure ---
GAME     : 'GAME' ;
VS       : 'vs' ;
QUARTER  : 'QUARTER' ;
OT       : 'OT' ;
END      : 'END' ;

// --- Keywords: teams ---
HOME     : 'HOME' ;
AWAY     : 'AWAY' ;

// --- Keywords: scoring ---
TWO_PT   : '2pt' ;
THREE_PT : '3pt' ;
FT       : 'ft' ;
MISS     : 'miss' ;

// --- Keywords: stats ---
REB_OFF  : 'reb_off' ;
REB_DEF  : 'reb_def' ;
AST      : 'ast' ;
STL      : 'stl' ;
BLK      : 'blk' ;
TO       : 'to' ;

// --- Keywords: fouls ---
FOUL_P   : 'foul_personal' ;
FOUL_T   : 'foul_technical' ;
FOUL_F   : 'foul_flagrant' ;
FOUL_OUT : 'foul_out' ;

// --- Keywords: achievements ---
DOUBLE_DOUBLE : 'double_double' ;
TRIPLE_DOUBLE : 'triple_double' ;

// --- Keywords: stat names (if)---
STAT_PTS : 'points' ;
STAT_REB : 'rebounds' ;
STAT_AST : 'assists' ;
STAT_STL : 'steals' ;
STAT_BLK : 'blocks' ;
STAT_FLS : 'fouls' ;
STAT_TO  : 'turnovers' ;

// --- Keywords: if ---
IF       : 'if' ;
ELSE     : 'else' ;
AND      : 'and' ;

// --- Operators ---
GTE      : '>=' ;

// --- Separators ---
HASH     : '#' ;
SEMI     : ';' ;
LCURLY   : '{' ;
RCURLY   : '}' ;

// --- Literals ---
INT      : [0-9]+ ;
ID       : [a-zA-Z_][a-zA-Z_0-9]* ;
WS       : [ \t\n\r\f]+ -> skip ;