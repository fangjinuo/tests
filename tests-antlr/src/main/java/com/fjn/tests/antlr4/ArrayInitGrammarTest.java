package com.fjn.tests.antlr4;


import com.fjn.tests.antlr4.arrayinit.ArrayInitLexer;
import com.fjn.tests.antlr4.arrayinit.ArrayInitListener;
import com.fjn.tests.antlr4.arrayinit.ArrayInitParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

@SuppressWarnings("unused")
public class ArrayInitGrammarTest {
    public static void main(String[] args) throws Exception {
        ANTLRInputStream inputStream = new ANTLRInputStream("{1,  2,3,{11,    12,13}, 4, {25,26}}");
        ArrayInitLexer lexer = new ArrayInitLexer(inputStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ArrayInitParser parser = new ArrayInitParser(tokens);
        ParseTree syntaxTree = parser.expr();

        System.out.println(syntaxTree.toStringTree(parser));

        ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
        parseTreeWalker.walk(new ArrayInitListener() {

            @Override
            public void enterExpr(ArrayInitParser.ExprContext ctx) {
                System.out.println("enterExpr");
            }

            @Override
            public void exitExpr(ArrayInitParser.ExprContext ctx) {
                System.out.println("enterExpr");
            }

            @Override
            public void enterValue(ArrayInitParser.ValueContext ctx) {
                System.out.println("enterValue");
            }

            @Override
            public void exitValue(ArrayInitParser.ValueContext ctx) {
                System.out.println("exitValue");
            }

            @Override
            public void visitTerminal(TerminalNode node) {
                System.out.println("visitTerminal");
                System.out.println(node.getText());
            }

            @Override
            public void visitErrorNode(ErrorNode node) {
                System.out.println("visitErrorNode");
                System.out.println(node.getText());
            }

            @Override
            public void enterEveryRule(ParserRuleContext ctx) {
                System.out.println("enterEveryRule");
            }

            @Override
            public void exitEveryRule(ParserRuleContext ctx) {
                System.out.println("exitEveryRule");
            }
        }, syntaxTree);
    }
}
