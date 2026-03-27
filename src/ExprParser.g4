parser grammar ExprParser;
options { tokenVocab=ExprLexer; }

program
    : game+ boxscore_cmd? EOF
    ;

game
    : header quarter+
    ;

header
    : GAME HOME VS AWAY SEMI
    ;

quarter
    : QUARTER (INT | OT) (event | if_stat)* END SEMI
    ;

event
    : player_ref action SEMI
    ;

if_stat
    : IF condition LCURLY (event | if_stat)* RCURLY
      (ELSE LCURLY (event | if_stat)* RCURLY)?
    ;

condition
    : player_ref stat_name GTE INT
      (AND player_ref stat_name GTE INT)*
    ;

stat_name
    : STAT_PTS
    | STAT_REB
    | STAT_AST
    | STAT_STL
    | STAT_BLK
    | STAT_FLS
    | STAT_TO
    ;

player_ref
    : (HOME | AWAY) HASH INT
    ;

action
    : TWO_PT                #score_2pt
    | THREE_PT              #score_3pt
    | FT                    #score_ft
    | MISS                  #miss
    | REB_OFF               #reb_off
    | REB_DEF               #reb_def
    | AST                   #assist
    | STL                   #steal
    | BLK                   #block
    | TO                    #turnover
    | foul_action           #foul
    | DOUBLE_DOUBLE         #double_double
    | TRIPLE_DOUBLE         #triple_double
    | FOUL_OUT              #foul_out
    ;

foul_action
    : FOUL_P
    | FOUL_T
    | FOUL_F
    ;

boxscore_cmd
    : BOXSCORE SEMI
    ;