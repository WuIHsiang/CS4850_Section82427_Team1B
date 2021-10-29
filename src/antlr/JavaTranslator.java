package antlr;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import javafx.scene.control.TextArea;
//Currently on line 
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
	private static class classDeclarationVisitor extends JavaParserBaseVisitor<String>{
		private String classdeclaration;
		
		ClassBodyVisitor ClassBodyVisitor=new ClassBodyVisitor();
		
		public String visitclassDeclaration(JavaParser.ClassDeclarationContext ctx) {
			if (ctx.getRuleIndex() == 4) {
				ClassBodyVisitor.visitClassBody(ctx.classBody());
			}
			else
				classdeclaration = ctx.getText();
		return classdeclaration;
		}
	}
	private static class ClassBodyVisitor extends JavaParserBaseVisitor<String>{
		private String classbody;
		
		ClassBodyDeclarationVisitor classBodyDeclarationVisitor=new ClassBodyDeclarationVisitor();
		
		public String visitClassBody(JavaParser.ClassBodyContext ctx) {
			if (ctx.getRuleIndex() == 4) {
				//classBodyDeclarationVisitor.visitClassBodyDeclaration(ctx.classBodyDeclaration());
			}
			else
				classbody = ctx.getText();
		return classbody;
		}
	}
	private static class ClassBodyDeclarationVisitor extends JavaParserBaseVisitor<String>{
		private String classbodydeclaration;
		
		ModifierVisitor modifierVisitor=new ModifierVisitor();
		
		public String visitClassBodyDeclaration(JavaParser.ClassBodyDeclarationContext ctx) {
			if (ctx.getRuleIndex() == 4) {
				//modifierVisitor.visitModifier(ctx.modifier());
			}
			else
				classbodydeclaration = ctx.getText();
		return classbodydeclaration;
		}
	}
	private static class ModifierVisitor extends JavaParserBaseVisitor<String>{
		private String modifier;
		
		ClassOrInterfaceModifierVisitor classorinterfaceVisitor=new ClassOrInterfaceModifierVisitor();
		
		public String visitModifier(JavaParser.ModifierContext ctx) {
			if (ctx.getRuleIndex() == 4) {
				//classorinterfaceVisitor.visitExpressionList(ctx.classOrInterfaceModifier());
			}
			else
				modifier = ctx.getText();
		return modifier;
		}
	}
	private static class ClassOrInterfaceModifierVisitor extends JavaParserBaseVisitor<String>{
		private String modifier;
		
		ExpressionListVisitor expressionListVisitor=new ExpressionListVisitor();
		
		public String visitClassOrInterfaceModifier(JavaParser.ModifierContext ctx) {
			if (ctx.getRuleIndex() == 4) {
				//expressionListVisitor.visitExpressionList(ctx.classOrInterfaceModifier());
			}
			else
				modifier = ctx.getText();
		return modifier;
		}
	}
	private static class PublicVisitor extends JavaParserBaseVisitor<String>{
		private String Public;
		public String visitPublic(JavaParser.ClassOrInterfaceModifierContext ctx) {
			if(ctx.getText()=="public") {
				Public = ctx.getText();
			}
			return Public;
		 }
	}
	private static class StaticVisitor extends JavaParserBaseVisitor<String>{
		private String Static;
		public String visitStatic(JavaParser.ClassOrInterfaceModifierContext ctx) {
			if(ctx.getText()=="static") {
				Static = ctx.getText();
			}
			return Static;
		 }
	}
	private static class methodCallVisitor extends JavaParserBaseVisitor<String>{
		private String methodcall;
		
		ExpressionListVisitor expressionListVisitor=new ExpressionListVisitor();
		
		public String visitmethodCall(JavaParser.MethodCallContext ctx) {
			if (ctx.getRuleIndex() == 81) {
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
			if (ctx.getRuleIndex() == 83) {
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
			if (ctx.getRuleIndex() == 87) {
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
			if (ctx.getRuleIndex() == 47) {
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
	
	/*public static class classDeclarationVisitor extends JavaParserBaseVisitor<String>{
		 private String classdeclaration;
		 
		 public String visitclassdeclaration(JavaParser.LiteralContext ctx) {
			 classdeclaration = ctx.getText();
			 return  classdeclaration;
		 }
	}*/
}