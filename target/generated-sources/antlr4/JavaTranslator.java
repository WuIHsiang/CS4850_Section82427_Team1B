package antlr4;

public class JavaTranslator extends antlr4.JavaParserBaseVisitor<Void> {
    public Void visitCompilationUnit(JavaParser.ClassDeclarationContext ctx) {
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
    }
}//end class