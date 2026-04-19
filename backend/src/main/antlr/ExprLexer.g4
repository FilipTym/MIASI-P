lexer grammar ExprLexer;

// --- Keywords: structure ---
GAME    : 'GAME' ;
VS      : 'vs' ;
QUARTER : 'QUARTER' ;
OT      : 'OT' ;
END     : 'END' ;

// --- Keywords: teams ---
HOME    : 'HOME' ;
AWAY    : 'AWAY' ;

// --- Keywords: scoring ---
TWO_PT  : '2pt' ;
THREE_PT: '3pt' ;
FT      : 'ft' ;
MISS    : 'miss' ;

// --- Keywords: stats ---
REB_OFF : 'reb_off' ;
REB_DEF : 'reb_def' ;
AST     : 'ast' ;
STL     : 'stl' ;
BLK     : 'blk' ;
TO      : 'to' ;

// --- Keywords: fouls ---
FOUL_P  : 'foul_personal' ;
FOUL_T  : 'foul_technical' ;
FOUL_F  : 'foul_flagrant' ;

// --- Keywords: output ---
BOXSCORE : 'BOXSCORE' ;

// --- Tokens ---
HASH    : '#' ;
SEMI    : ';' ;
INT     : [0-9]+ ;
ID      : [a-zA-Z_][a-zA-Z_0-9]* ;
WS      : [ \t\n\r\f]+ -> skip ;