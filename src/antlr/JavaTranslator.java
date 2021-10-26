package antlr;

import org.antlr.v4.runtime.CommonTokenStream;

public class JavaTranslator extends antlr4.JavaParserBaseVisitor<Void> {
	public TypeVisitor parse(JavaParser parser) {
		TypeVisitor typeVisitor = new TypeVisitor();
		Type traverseResult = typeVisitor.visit(parser.compilationUnit());
		return traverseResult;
	}
	
	private static class TypeVisitor extends JavaParserBaseVisitor<Class>{
		//fuck this class
		}
	}
	
    /* public Void visitCompilationUnit(JavaParser.ClassDeclarationContext ctx) {
        String comp = ctx.getText();

        System.out.println(comp);
        return null;
    }

    public Void visitTypeDeclaration(JavaParser.TypeDeclarationContext ctx) {
        String comp = ctx.getText();

        System.out.println(comp);
        return null;
    }

    public Void visitClassDeclaration(JavaParser.ClassDeclarationContext ctx){
        String className = ctx.getText();

        System.out.println(className);

        return null;
    } */
}//end class