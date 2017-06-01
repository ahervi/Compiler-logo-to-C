grammar Logo;



prog
   : (procDecl | cmd) +
   ;

procDecl
   : 'to' NAME formals body 'end'

   ;
formals
   : (':' NAME) *
   ;
body
   : cmd*
   ;
cmd
   : 'repeat' expr block                                    # RepeatCmd
   | 'if' expr block                                        # IfCmd
   | 'ifelse' expr block block                              # IfElseCmd
   | 'make' '"' NAME expr                                   # Assign
   | call                                                   # CallCmd

   ;
expr
   : NUMBER                                                 # NumLit
   | BOOLEAN                                                # BoolLit
   | '"' NAME                                               # Symbol
   | ':' NAME                                               # Deref
   // simplified form of list litteral
   | '[' NAME* ']'                                          # ListLit
   | PRIMFUNC1 expr                                         # FuncCall1
   | expr ('*'|'/') expr                                    # BinExpr
   | expr ('+'|'-') expr                                    # BinExpr
   | expr ('<'| '<=' | '>=' | '=' | '<>' | '>') expr        # BinExpr
   | '(' expr ')'                                           # ParExpr
   | 'xcor'                                                 #XCor
   | 'ycor'                                                 #YCor


   ;
block
   : '[' cmd* ']'
   ;
call
   : PRIMPROC0                                              # PrimCall0
   | PRIMPROC1 expr                                         # PrimCall1
   | NAME expr*                                             # UserCall
   ;


BOOLEAN
   : 'true'
   | 'false'
   ;
PRIMFUNC1
   : 'pick'
   | 'random'

   ;
PRIMPROC0
   : 'cs' | 'clearscreen'
   | 'stop'
   ;
PRIMPROC1
   : 'forward' | 'fd'
   | 'back' |'bk'
   | 'right' | 'rt'
   | 'left' | 'lt'
   | 'setpencolor'
   | 'show'
   ;


NAME // simplified version
   : [a-zA-Z] [a-zA-Z0-9_]*
   ;

NUMBER
    :   ' -'? INT '.' INT EXP?                                      // 1.35, 1.35E-9, 0.3, -4.5
    | '(' ('-' | ' -' | '- ')? INT '.' INT EXP? ( ')' | ' )' )      // (-1.35), ( -1.35E-9), (- 0.3), (- 4.5)
    |   ' -'? INT EXP                                               // 1e10 -3e4
    |   '(' ('-' | ' -' | '- ')? INT EXP ( ')' | ' )' )             // (1e10) ( -3e4), ( -3e4 ), (-3e4 )
    |   ' -'? INT                                                   // -3, 45
    |   '(' ('-' | ' -' | '- ')? INT ( ')' | ' )' )                 // (-3), ( -45), (- 45 ), (-45 )
    ;

fragment INT :   '0' | [1-9] [0-9]* ; // no leading zeros
fragment EXP :   [Ee] [+\-]? INT ;
LINE_COMMENT : ';' ~'\n'* '\n' -> channel(HIDDEN) ;

WS : [ \t\n\r]+ -> channel(HIDDEN) ;

