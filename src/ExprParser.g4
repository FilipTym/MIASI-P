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
    : QUARTER (INT | OT) event* END SEMI
    ;

event
    : player_ref action SEMI
    ;

player_ref
    : (HOME | AWAY) HASH INT
    ;

action
    : TWO_PT                   #score_2pt
    | THREE_PT                 #score_3pt
    | FT                       #score_ft
    | MISS                     #miss
    | REB_OFF                  #reb_off
    | REB_DEF                  #reb_def
    | AST                      #assist
    | STL                      #steal
    | BLK                      #block
    | TO                       #turnover
    | foul_action              #foul
    ;

foul_action
    : FOUL_P
    | FOUL_T
    | FOUL_F
    ;

boxscore_cmd
    : BOXSCORE SEMI
BOXSCORE;