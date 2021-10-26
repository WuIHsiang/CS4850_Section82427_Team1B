package antlr;

import org.antlr.v4.runtime.CommonTokenStream;

public class JavaTranslator extends antlr.JavaParserBaseVisitor<Void> {
	/*public TypeVisitor parse(JavaParser parser) {
		TypeVisitor typeVisitor = new TypeVisitor();
		Type traverseResult = typeVisitor.visit(parser.compilationUnit());
		return traverseResult;
	}*/
	
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
}