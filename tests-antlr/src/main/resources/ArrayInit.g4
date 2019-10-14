grammar ArrayInit;
import CommonLexerGrammar;

expr : '{' value (',' value)* '}' ;

value : expr | INT;

