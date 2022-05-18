grammar Jython;
program : importclass* (classDef)? ;
importclass : IMPORT CLASSNAME ;
classDef : CLASS className=CLASSNAME (OPEN_PARANTESES classParent=CLASSNAME (COMMA classParent2=CLASSNAME)* CLOSE_PARANTESES)? OPEN_BRACES class_body* CLOSE_BRACES;
class_body : varDec | methodDec | constructor | arrayDec ;
varDec : (varType=TYPE | varClassName=CLASSNAME) varId=ID ;
arrayDec : (arrType=TYPE | arrClassName=CLASSNAME) OPEN_BRACKET INTEGER CLOSE_BRACKET arrId=ID ;
methodDec : DEF (methodType=(TYPE | CLASSNAME)|VOID) methodId=ID OPEN_PARANTESES parameter* CLOSE_PARANTESES OPEN_BRACES ( methodStatement=statement)* CLOSE_BRACES;
constructor : DEF (TYPE | CLASSNAME) OPEN_PARANTESES constructorParams=parameter* CLOSE_PARANTESES OPEN_BRACES ( statement)* CLOSE_BRACES ;
parameter : varDec (COMMA  varDec)* ;
statement : varDec | assignment |print_statment | method_call | return_statment
|if_statment | while_statment | if_else_statment | for_statment;
return_statment : RETURN exp ;
condition_list : condition ((OR|AND) condition)* ;
condition : BOOL | prefixexp | (exp) relational_operators (exp);
if_statment : IF OPEN_PARANTESES condition_list CLOSE_PARANTESES OPEN_BRACES statement* CLOSE_BRACES;
while_statment : WHILE OPEN_PARANTESES condition_list CLOSE_PARANTESES OPEN_BRACES statement* CLOSE_BRACES ;
if_else_statment :IF OPEN_PARANTESES condition_list CLOSE_PARANTESES OPEN_BRACES statement* CLOSE_BRACES (ELIF OPEN_PARANTESES condition_list CLOSE_PARANTESES OPEN_BRACES statement* CLOSE_BRACES)* ELSE OPEN_BRACES statement* CLOSE_BRACES ;
print_statment : PRINT OPEN_PARANTESES (prefixexp | (printStatementType=TYPE | printStatementClassName=CLASSNAME) args | INTEGER |STRING | BOOL) CLOSE_PARANTESES ;
for_statment : FOR ID IN ID OPEN_BRACES statement* CLOSE_BRACES
| FOR ID IN RANGE OPEN_PARANTESES INTEGER (COMMA INTEGER)? (COMMA INTEGER)? CLOSE_PARANTESES OPEN_BRACES statement* CLOSE_BRACES ;
method_call : methodCallId=ID (DOT)? args ;
assignment : prefixexp assignment_operators exp
| varDec assignment_operators exp
| arrayDec ASSIGN (assignType=TYPE |assignClassName= CLASSNAME) args (OPEN_BRACKET INTEGER CLOSE_BRACKET) ;
exp :NONE | BOOL | INTEGER | STRING | FLOAT | prefixexp | exp arithmetic_operator exp
| (expType=TYPE |expClassName= CLASSNAME) args | OPEN_PARANTESES exp CLOSE_PARANTESES | expId=ID args ;
prefixexp : ID | prefixexp OPEN_BRACKET INTEGER CLOSE_BRACKET | prefixexp DOT ID | prefixexp DOT ID args ;
args : OPEN_PARANTESES (explist)? CLOSE_PARANTESES ;
explist : exp (COMMA exp)*;
arithmetic_operator: ADD | SUB | MUL | DIV | MOD ;
relational_operators : LT | GT | LE | GE | EQUAL |  NOTEQUAL;
assignment_operators : ASSIGN | ADD_ASSIGN | SUB_ASSIGN | MUL_ASSIGN | DIV_ASSIGN ;





//ARITHMATIC OPERATOR
ADD:                '+';
SUB:                '-';
MUL:                '*';
DIV:                '/';
MOD:                '%';

//RELATIONAL OPERATOR
GT:                 '>';
LT:                 '<';
EQUAL:              '==';
LE:                 '<=';
GE:                 '>=';
NOTEQUAL:           '!=';

//ASSIGNMENT OPERATOR
ASSIGN:             '=';
ADD_ASSIGN:         '+=';
SUB_ASSIGN:         '-=';
MUL_ASSIGN:         '*=';
DIV_ASSIGN:         '/=';
MOD_ASSIGN:         '%=';

//KEYWORDS
CLASS:              'class';
IMPORT:             'import';
DEF:                'def';
VOID:               'void';
RETURN:             'return';
AND:                'and';
OR:                 'or';
IF:                 'if';
WHILE:              'while';
ELIF:               'elif';
ELSE:               'else';
PRINT:              'print';
FOR:                'for';
IN:                 'in';
RANGE:              'range';
NONE:               'none';

//PUNCTUATIONS
OPEN_BRACKET : '[' ;
CLOSE_BRACKET :']';
OPEN_PARANTESES :'(';
CLOSE_PARANTESES : ')';
OPEN_BRACES : '{';
CLOSE_BRACES :'}';
COMMA : ',';
DOT : '.';

TYPE: INT | FLOATING_POINT | STRING | BOOL;
INT: 'int';
FLOATING_POINT: 'float';
STRING: 'string';
BOOL: 'bool';
CLASSNAME: [A-Z] (LETTER|DIGIT)*;
ID: [a-z]([A-Za-z_])*;
LETTER: [A-Za-z];
INTEGER: DIGIT+;
FLOAT: (DIGIT)+ DOT (DIGIT)+;
DIGIT: [0-9];


OPEN_COMMENT : '#*' ;
CLOSE_COMMENT : '*#' ;
COMMENT : OPEN_COMMENT (COMMENT | .)*? CLOSE_COMMENT -> skip ;
ONE_LINE_COMMENT : '#' (~ '\n')* '\n'? -> skip ;

WHITESPACE : [ \t\r\n\f]+ -> skip ;




