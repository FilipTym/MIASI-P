parser grammar ExprParser;
options { tokenVocab=ExprLexer; }

program
    : rules_section? game+ boxscore_cmd? EOF
    ;

// --- Reguły na początku programu ---
rules_section
    : RULES rule_def+ END SEMI
    ;

rule_def
    : ID EQ (INT | ID) SEMI
    ;

game
    : header quarter+
    ;

header
    : GAME team_name VS team_name SEMI
    ;

team_name
    : ID
    ;

quarter
    : QUARTER (INT | OT) event* END SEMI
    ;

event
    : player_ref action SEMI      #playerEvent
    | substitution SEMI           #substitutionEvent
    ;

player_ref
    : team_name HASH INT
    ;

substitution
    : team_name SUB_IN HASH INT
    | team_name SUB_OUT HASH INT
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
    ;