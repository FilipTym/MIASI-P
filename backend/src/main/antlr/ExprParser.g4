parser grammar ExprParser;
options { tokenVocab=ExprLexer; }

program
    : rulesSection? game+ boxscore_cmd? EOF
    ;

// ---------------------------------------------------------------------------
// RULES block — numeric settings + mandatory rosters for both teams
//
// Example:
//   RULES
//       players_on_court = 5;
//       quarters         = 4;
//       quarter_length   = 12;
//
//       ROSTER Lakers: #5, #23, #3;
//       ROSTER Celtics: #0, #11, #7;
//   END;
// ---------------------------------------------------------------------------
rulesSection
    : RULES ruleItem+ END SEMI
    ;

// A ruleItem is either a key=value setting or a team roster declaration.
ruleItem
    : ruleDef       #ruleDefItem
    | rosterDef     #rosterDefItem
    ;

ruleDef
    : ID EQ (INT | ID) SEMI
    ;

// ROSTER <teamRef> : #<number> (, #<number>)* ;
rosterDef
    : ROSTER teamRef COLON playerEntry (COMMA playerEntry)* SEMI
    ;

// One player in a roster: just their jersey number — #23
playerEntry
    : HASH INT
    ;

// ---------------------------------------------------------------------------
// GAME header — full name with optional short alias
//
// Examples (both are valid):
//   GAME Lakers vs Celtics;
//   GAME Lakers as LAL vs Celtics as BOS;
// ---------------------------------------------------------------------------
game
    : header quarter+
    ;

header
    : GAME teamDecl VS teamDecl SEMI
    ;

// Full team name with an optional alias: Lakers as LAL
teamDecl
    : teamRef (AS teamAlias)?
    ;

// The alias is a single ID token (e.g. LAL, BOS, GSW).
teamAlias
    : ID
    ;

// ---------------------------------------------------------------------------
// Reusable rule: reference to a team — just one ID token.
// Used in header, rosters, player_ref, and substitution.
// ---------------------------------------------------------------------------
teamRef
    : ID
    ;

// ---------------------------------------------------------------------------
// Quarters & events
// ---------------------------------------------------------------------------
quarter
    : QUARTER (INT | OT) event* END SEMI
    ;

event
    : player_ref action SEMI    #playerEvent
    | substitution SEMI         #substitutionEvent
    ;

// Player reference inside a quarter event: <teamRef_or_alias> #<number>
player_ref
    : teamRef HASH INT
    ;

substitution
    : teamRef SUB_IN  HASH INT
    | teamRef SUB_OUT HASH INT
    ;

// ---------------------------------------------------------------------------
// Actions
// ---------------------------------------------------------------------------
action
    : TWO_PT        #score_2pt
    | THREE_PT      #score_3pt
    | FT            #score_ft
    | MISS          #miss
    | REB_OFF       #reb_off
    | REB_DEF       #reb_def
    | AST           #assist
    | STL           #steal
    | BLK           #block
    | TO            #turnover
    | foul_action   #foul
    ;

foul_action
    : FOUL_P
    | FOUL_T
    | FOUL_F
    ;

// ---------------------------------------------------------------------------
// BOXSCORE command
// ---------------------------------------------------------------------------
boxscore_cmd
    : BOXSCORE SEMI
    ;
