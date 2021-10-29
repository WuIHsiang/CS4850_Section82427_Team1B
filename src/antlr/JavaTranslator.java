package antlr;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import javafx.scene.control.TextArea;

public class JavaTranslator extends antlr.JavaParserBaseVisitor<Void> {
	/*public TypeVisitor parse(JavaParser parser) {
		TypeVisitor typeVisitor = new TypeVisitor();
		Type traverseResult = typeVisitor.visit(parser.compilationUnit());
		return traverseResult;
	}*/
	static public boolean translate(TextArea ta) {
		boolean success = false;
		
		String content = ta.getText();
        //System.out.println("Java File:\n" + content + "\n\n");
        
        ANTLRInputStream input = new ANTLRInputStream(content);
        
        antlr.JavaLexer lexer = new antlr.JavaLexer(input);
        
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        
        antlr.JavaParser parser = new antlr.JavaParser(tokens);
        
        antlr.JavaTranslator translator = new antlr.JavaTranslator();
        
        // translator.visit(parser.compilationUnit());
        
        // translator.visit(parser.classDeclaration());
        
        ParseTree tree = parser.compilationUnit();
        
        System.out.println("ParseTree:\n" + tree.toStringTree(parser) + "\n");
        String[] treearr=tree.toStringTree(parser).split("(");
        for(int i=0;i<treearr.length;i++) {
        	System.out.println(treearr[i]);
        }
      
     
		return success;
	}
	private static class methodCallVisitor extends JavaParserBaseVisitor<String>{
		private String methodcall;
		
		ExpressionListVisitor expressionListVisitor=new ExpressionListVisitor();
		
		public String visitmethodCall(JavaParser.MethodCallContext ctx) {
			if (ctx.getRuleIndex() == 4) {
				expressionListVisitor.visitExpressionList(ctx.expressionList());
			}
			else
				methodcall = ctx.getText();
		return methodcall;
		}
	}
	private static class ExpressionListVisitor extends JavaParserBaseVisitor<String>{
		private String expressionlist;
		
		ExpressionVisitor expressionVisitor=new ExpressionVisitor();
		
		public String visitExpressionList(JavaParser.ExpressionListContext ctx) {
			if (ctx.getRuleIndex() == 4) {
				expressionVisitor.visitExpression(ctx.expression(0));
			}
			else
				expressionlist = ctx.getText();
		return expressionlist;
		}
	}
	private static class ExpressionVisitor extends JavaParserBaseVisitor<String>{
		private String expression;
		
		PrimaryVisitor primaryVisitor = new PrimaryVisitor();
		
		public String visitExpression(JavaParser.ExpressionContext ctx) {
			if (ctx.getRuleIndex() == 4) {
				primaryVisitor.visitPrimary(ctx.primary());
			}
			else
				expression = ctx.getText();
		return expression;
		}
	}
	 
	private static class PrimaryVisitor extends JavaParserBaseVisitor<String>{
		private String primary;
		
		LiteralVisitor literalVisitor = new LiteralVisitor();
		
		public String visitPrimary(JavaParser.PrimaryContext ctx) {
			if (ctx.getRuleIndex() == 4) {
				literalVisitor.visitLiteral(ctx.literal());
			}
			else
				primary = ctx.getText();
		return primary;
		}
	}
	
	public static class LiteralVisitor extends JavaParserBaseVisitor<String>{
		 private String literal;
		 
		 public String visitLiteral(JavaParser.LiteralContext ctx) {
			 literal = ctx.getText();
			 return literal;
		 }
	}
	
	public static class classDeclarationVisitor extends JavaParserBaseVisitor<String>{
		 private String classdeclaration;
		 
		 public String visitclassdeclaration(JavaParser.LiteralContext ctx) {
			 classdeclaration = ctx.getText();
			 return  classdeclaration;
		 }
	}
}