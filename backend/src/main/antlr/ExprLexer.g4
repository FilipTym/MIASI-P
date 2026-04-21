lexer grammar ExprLexer;

// --- Keywords: structure ---
GAME    : 'GAME' ;
VS      : 'vs' ;
QUARTER : 'QUARTER' ;
OT      : 'OT' ;
END     : 'END' ;
RULES   : 'RULES' ;

// --- Keywords: substitutions ---
SUB_IN  : 'sub_in' ;
SUB_OUT : 'sub_out' ;

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

// --- Keywords: fouls (full + shortcuts) ---
FOUL_P  : 'personal_foul' | 'pf' ;
FOUL_T  : 'technical_foul' | 'tf' ;
FOUL_F  : 'flagrant_foul' | 'ff' ;

// --- Keywords: output ---
BOXSCORE : 'BOXSCORE' ;

// --- Tokens ---
HASH    : '#' ;
SEMI    : ';' ;
COLON   : ':' ;
EQ      : '=' ;
INT     : [0-9]+ ;
ID      : [a-zA-Z_][a-zA-Z_0-9]* ;
WS      : [ \t\n\r\f]+ -> skip ;