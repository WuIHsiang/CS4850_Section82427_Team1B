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
	/*
	 
		
		RULE_typeParameters = 8, 
		RULE_typeParameter = 9, RULE_typeBound = 10, RULE_enumDeclaration = 11, 
		RULE_enumConstants = 12, RULE_enumConstant = 13, RULE_enumBodyDeclarations = 14, 
		 RULE_genericMethodDeclaration = 23, 
		RULE_genericConstructorDeclaration = 24, RULE_constructorDeclaration = 25, 
		RULE_fieldDeclaration = 26,
		RULE_constDeclaration = 29, RULE_constantDeclarator = 30, RULE_interfaceMethodDeclaration = 31, 
		RULE_interfaceMethodModifier = 32, RULE_genericInterfaceMethodDeclaration = 33, 
		RULE_variableDeclarators = 34, RULE_variableDeclarator = 35, 
		RULE_variableInitializer = 37, RULE_arrayInitializer = 38, RULE_classOrInterfaceType = 39, 
		RULE_typeArgument = 40, RULE_qualifiedNameList = 41,  RULE_lastFormalParameter = 45, 
		RULE_qualifiedName = 46,  RULE_integerLiteral = 48, 
		RULE_floatLiteral = 49, RULE_altAnnotationQualifiedName = 50, RULE_annotation = 51, 
		RULE_elementValuePairs = 52, RULE_elementValuePair = 53, RULE_elementValue = 54, 
		RULE_elementValueArrayInitializer = 55, RULE_annotationTypeDeclaration = 56, 
		RULE_annotationTypeBody = 57, RULE_annotationTypeElementDeclaration = 58, 
		RULE_annotationTypeElementRest = 59, RULE_annotationMethodOrConstantRest = 60, 
		RULE_annotationMethodRest = 61, RULE_annotationConstantRest = 62, RULE_defaultValue = 63, 
		RULE_localVariableDeclaration = 66, RULE_localTypeDeclaration = 67,  RULE_catchClause = 69, 
		RULE_catchType = 70, RULE_finallyBlock = 71, RULE_resourceSpecification = 72, 
		RULE_resources = 73, RULE_resource = 74, RULE_switchBlockStatementGroup = 75, 
		RULE_switchLabel = 76, RULE_forControl = 77, RULE_forInit = 78, RULE_enhancedForControl = 79, 
		RULE_parExpression = 80, RULE_lambdaExpression = 84, RULE_lambdaParameters = 85, 
		RULE_lambdaBody = 86,  RULE_classType = 88, RULE_creator = 89, 
		RULE_createdName = 90, RULE_innerCreator = 91, RULE_arrayCreatorRest = 92, 
		RULE_classCreatorRest = 93, RULE_explicitGenericInvocation = 94, RULE_typeArgumentsOrDiamond = 95, 
		RULE_nonWildcardTypeArgumentsOrDiamond = 96, RULE_nonWildcardTypeArguments = 97, 
		RULE_typeList = 98,  RULE_primitiveType = 100, RULE_typeArguments = 101, 
		RULE_superSuffix = 102, RULE_explicitGenericInvocationSuffix = 103, RULE_arguments = 104;
	 */
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
	private static class compilationUnitVisitor extends JavaParserBaseVisitor<String>{
		private String compilationUnit;
		
		typeDeclarationVisitor TypeDeclarationVisitor=new typeDeclarationVisitor();
		importDeclarationVisitor ImportDeclarationVisitor=new importDeclarationVisitor();
		packageDeclarationVisitor PackageDeclarationVisitor=new packageDeclarationVisitor();
		
		public String visitcompilationUnit(JavaParser.CompilationUnitContext ctx) {
			if (ctx.getRuleIndex() == 3) {
				TypeDeclarationVisitor.visittypeDeclaration(ctx.typeDeclaration(0));
			}else if (ctx.getRuleIndex() == 2) {
				ImportDeclarationVisitor.visitimportDeclaration(ctx.importDeclaration(0));
			}else if (ctx.getRuleIndex() == 1) {
				PackageDeclarationVisitor.visitpackageDeclaration(ctx.packageDeclaration());
			}else
				compilationUnit = ctx.getText();
		return compilationUnit;
		}
	}
	private static class importDeclarationVisitor extends JavaParserBaseVisitor<String>{
		private String importdeclaration;
		
		public String visitimportDeclaration(JavaParser.ImportDeclarationContext ctx) {
			importdeclaration= ctx.getText();
			return importdeclaration;
		}
	}
	private static class packageDeclarationVisitor extends JavaParserBaseVisitor<String>{
		private String packagedeclaration;
		
		public String visitpackageDeclaration(JavaParser.PackageDeclarationContext ctx) {
			packagedeclaration= ctx.getText();
			return packagedeclaration;
		}
	}
	private static class typeDeclarationVisitor extends JavaParserBaseVisitor<String>{
		private String typedeclaration;
		
		classDeclarationVisitor ClassDeclarationVisitor=new classDeclarationVisitor();
		interfaceDeclarationVisitor InterfaceDeclarationVisitor=new interfaceDeclarationVisitor();
		
		public String visittypeDeclaration(JavaParser.TypeDeclarationContext ctx) {
			if (ctx.getRuleIndex() == 7) {
				ClassDeclarationVisitor.visitclassDeclaration(ctx.classDeclaration());
			}else if (ctx.getRuleIndex() == 15) {
				InterfaceDeclarationVisitor.visitinterfaceDeclaration(ctx.interfaceDeclaration());
			}
			else
				typedeclaration = ctx.getText();
		return typedeclaration;
		}
	}
	private static class classDeclarationVisitor extends JavaParserBaseVisitor<String>{
		private String classdeclaration;
		
		ClassBodyVisitor ClassBodyVisitor=new ClassBodyVisitor();
		
		public String visitclassDeclaration(JavaParser.ClassDeclarationContext ctx) {
			if (ctx.getRuleIndex() == 16) {
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
			if (ctx.getRuleIndex() == 18) {
				classBodyDeclarationVisitor.visitClassBodyDeclaration(ctx.classBodyDeclaration(0));
			}
			else
				classbody = ctx.getText();
		return classbody;
		}
	}
	private static class ClassBodyDeclarationVisitor extends JavaParserBaseVisitor<String>{
		private String classbodydeclaration;
		
		ModifierVisitor modifierVisitor=new ModifierVisitor();
		memberDeclarationVisitor memberdeclarationVisitor=new memberDeclarationVisitor();
		
		public String visitClassBodyDeclaration(JavaParser.ClassBodyDeclarationContext ctx) {
			if (ctx.getRuleIndex() == 4) {
				modifierVisitor.visitModifier(ctx.modifier(0));
			}else if (ctx.getRuleIndex() == 19) {
				memberdeclarationVisitor.visitmemberDeclaration(ctx.memberDeclaration());
			}
			else
				classbodydeclaration = ctx.getText();
		return classbodydeclaration;
		}
	}
	private static class interfaceDeclarationVisitor extends JavaParserBaseVisitor<String>{
		private String classdeclaration;
		
		InterfaceBodyVisitor interfaceBodyVisitor=new InterfaceBodyVisitor();
		
		public String visitinterfaceDeclaration(JavaParser.InterfaceDeclarationContext ctx) {
			if (ctx.getRuleIndex() == 17) {
				interfaceBodyVisitor.visitInterfaceBody(ctx.interfaceBody());
			}
			else
				classdeclaration = ctx.getText();
		return classdeclaration;
		}
	}
	private static class InterfaceBodyVisitor extends JavaParserBaseVisitor<String>{
		private String interfacebody;
		
		InterfaceBodyDeclarationVisitor interfaceBodyDeclarationVisitor = new InterfaceBodyDeclarationVisitor();
		
		public String visitInterfaceBody(JavaParser.InterfaceBodyContext ctx) {
			if (ctx.getRuleIndex() == 27) {
				interfaceBodyDeclarationVisitor.visitInterfaceBodyDeclaration(ctx.interfaceBodyDeclaration(0));
			}
			else
				interfacebody = ctx.getText();
		return interfacebody;
		}
	}
	private static class InterfaceBodyDeclarationVisitor extends JavaParserBaseVisitor<String>{
		private String interfacebodydeclaration;
		
		ModifierVisitor modifierVisitor=new ModifierVisitor();
		interfaceMemberDeclarationVisitor InterfaceMemberDeclarationVisitor=new interfaceMemberDeclarationVisitor();
		
		public String visitInterfaceBodyDeclaration(JavaParser.InterfaceBodyDeclarationContext ctx) {
			if (ctx.getRuleIndex() == 4) {
				modifierVisitor.visitModifier(ctx.modifier(0));
			}else if(ctx.getRuleIndex() == 28) {
				InterfaceMemberDeclarationVisitor.visitinterfaceMemberDeclaration(ctx.interfaceMemberDeclaration());
			}
			else
				interfacebodydeclaration = ctx.getText();
		return interfacebodydeclaration;
		}
	}
	
	private static class ModifierVisitor extends JavaParserBaseVisitor<String>{
		private String modifier;
		
		ClassOrInterfaceModifierVisitor classorinterfaceVisitor=new ClassOrInterfaceModifierVisitor();
		VariableModifierVisitor variableVisitor=new VariableModifierVisitor();
		
		public String visitModifier(JavaParser.ModifierContext ctx) {
			if (ctx.getRuleIndex() == 5) {
				classorinterfaceVisitor.visitClassOrInterfaceModifier(ctx.classOrInterfaceModifier());
			}else if (ctx.getRuleIndex() == 6) {
				//variableVisitor.visitVariableModifier(ctx.);
			}
			else
				modifier = ctx.getText();
		return modifier;
		}
	}
	private static class ClassOrInterfaceModifierVisitor extends JavaParserBaseVisitor<String>{
		private String classorinterfacemodifier;
		
		public String visitClassOrInterfaceModifier(JavaParser.ClassOrInterfaceModifierContext ctx) {
			classorinterfacemodifier = ctx.getText();
			return classorinterfacemodifier;
		}
	}
	private static class VariableModifierVisitor extends JavaParserBaseVisitor<String>{
		private String variablemodifier;
		
		public String visitVariableModifier(JavaParser.VariableModifierContext ctx) {
			variablemodifier = ctx.getText();
			return variablemodifier;
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
	private static class memberDeclarationVisitor extends JavaParserBaseVisitor<String>{
		private String memberdeclaration;
		
		methodDeclarationVisitor methoddeclarationVisitor=new methodDeclarationVisitor();
		
		public String visitmemberDeclaration(JavaParser.MemberDeclarationContext ctx) {
			if (ctx.getRuleIndex() == 20) {
				methoddeclarationVisitor.visitmethodDeclaration(ctx.methodDeclaration());
			}
			else
				memberdeclaration = ctx.getText();
		return memberdeclaration;
		}
	}
	private static class methodDeclarationVisitor extends JavaParserBaseVisitor<String>{
		private String methoddeclaration;
		
		methodBodyVisitor methodbodyVisitor=new methodBodyVisitor();
		typeTypeorVoidVisitor typeTypeorVoidVisitor=new typeTypeorVoidVisitor();
		formalParametersVisitor FormalParametersVisitor=new formalParametersVisitor();
		
		public String visitmethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
			if (ctx.getRuleIndex() == 21) {
				methodbodyVisitor.visitmethodBody(ctx.methodBody());
			}else if (ctx.getRuleIndex() == 22) {
				typeTypeorVoidVisitor.visittypeTypeorVoid(ctx.typeTypeOrVoid());
			}else if (ctx.getRuleIndex() == 42) {
				FormalParametersVisitor.visitformalParameters(ctx.formalParameters());
			}else
				methoddeclaration = ctx.getText();
		return methoddeclaration;
		}
	}
	private static class interfaceMemberDeclarationVisitor extends JavaParserBaseVisitor<String>{
		private String interfacememberdeclaration;
		
		interfaceMethodDeclarationVisitor interfacemethoddeclarationVisitor=new interfaceMethodDeclarationVisitor();
		
		public String visitinterfaceMemberDeclaration(JavaParser.InterfaceMemberDeclarationContext ctx) {
			if (ctx.getRuleIndex() == 31) {
				interfacemethoddeclarationVisitor.visitmethodDeclaration(ctx.interfaceMethodDeclaration());
			}
			else
				interfacememberdeclaration = ctx.getText();
		return interfacememberdeclaration;
		}
	}
	private static class interfaceMethodDeclarationVisitor extends JavaParserBaseVisitor<String>{
		private String interfacemethoddeclaration;
		
		methodBodyVisitor methodbodyVisitor=new methodBodyVisitor();
		typeTypeorVoidVisitor typeTypeorVoidVisitor=new typeTypeorVoidVisitor();
		formalParametersVisitor FormalParametersVisitor=new formalParametersVisitor();
		
		public String visitmethodDeclaration(JavaParser.InterfaceMethodDeclarationContext ctx) {
			if (ctx.getRuleIndex() == 21) {
				methodbodyVisitor.visitmethodBody(ctx.methodBody());
			}else if (ctx.getRuleIndex() == 22) {
				typeTypeorVoidVisitor.visittypeTypeorVoid(ctx.typeTypeOrVoid());
			}else if (ctx.getRuleIndex() == 42) {
				FormalParametersVisitor.visitformalParameters(ctx.formalParameters());
			}else
				interfacemethoddeclaration = ctx.getText();
		return interfacemethoddeclaration;
		}
	}
	private static class formalParametersVisitor extends JavaParserBaseVisitor<String>{
		private String formalparameters;
		
		formalParameterListVisitor FormalParameterListVisitor=new formalParameterListVisitor();
		
		public String visitformalParameters(JavaParser.FormalParametersContext ctx) {
			if (ctx.getRuleIndex() == 43) {
				FormalParameterListVisitor.visitformalParameterList(ctx.formalParameterList());
			}
			else
				formalparameters = ctx.getText();
		return formalparameters;
		}
	}
	private static class formalParameterListVisitor extends JavaParserBaseVisitor<String>{
		private String formalparameterlist;
		
		formalParameterVisitor FormalParameterVisitor=new formalParameterVisitor();
		
		public String visitformalParameterList(JavaParser.FormalParameterListContext ctx) {
			if (ctx.getRuleIndex() == 44) {
				FormalParameterVisitor.visitformalParameter(ctx.formalParameter(0));
			}
			else
				formalparameterlist = ctx.getText();
		return formalparameterlist;
		}
	}
	private static class formalParameterVisitor extends JavaParserBaseVisitor<String>{
		private String formalparameter;
		
		typeTypeVisitor TypeTypeVisitor=new typeTypeVisitor();
		VariableDeclaratorIdVisitor variableDeclaratorIdVisitor=new VariableDeclaratorIdVisitor();
		
		public String visitformalParameter(JavaParser.FormalParameterContext ctx) {
			if (ctx.getRuleIndex() == 99) {
				TypeTypeVisitor.visitTypeType(ctx.typeType());
			}else if (ctx.getRuleIndex() == 36) {
				variableDeclaratorIdVisitor.visitVariableDeclaratorId(ctx.variableDeclaratorId());
			}else
				formalparameter = ctx.getText();
		return formalparameter;
		}
	}
	private static class typeTypeVisitor extends JavaParserBaseVisitor<String>{
		private String typetype;
		
		classOrInterfaceTypeVisitor ClassOrInterfaceTypeVisitor=new classOrInterfaceTypeVisitor();
		
		public String visitTypeType(JavaParser.TypeTypeContext ctx) {
			if (ctx.getRuleIndex() == 39) {
				ClassOrInterfaceTypeVisitor.visitclassOrInterfaceType(ctx.classOrInterfaceType());
			}
			else
				typetype = ctx.getText();
		return typetype;
		}
	}
	private static class VariableDeclaratorIdVisitor extends JavaParserBaseVisitor<String>{
		private String variabledeclaratorid;
		
		public String visitVariableDeclaratorId(JavaParser.VariableDeclaratorIdContext ctx) {
			variabledeclaratorid = ctx.getText();
			return variabledeclaratorid;
		}
	}
	private static class classOrInterfaceTypeVisitor extends JavaParserBaseVisitor<String>{
		private String classorinterfacetype;
		
		public String visitclassOrInterfaceType(JavaParser.ClassOrInterfaceTypeContext ctx) {
			classorinterfacetype = ctx.getText();
			return classorinterfacetype;
		}
	}
	private static class typeTypeorVoidVisitor extends JavaParserBaseVisitor<String>{
		private String typetypeorvoid;
		
		public String visittypeTypeorVoid(JavaParser.TypeTypeOrVoidContext ctx) {
				typetypeorvoid = ctx.getText();
				return typetypeorvoid;
		}
	}
	private static class methodBodyVisitor extends JavaParserBaseVisitor<String>{
		private String methodbody;
		
		BlockVisitor blockVisitor=new BlockVisitor();
		
		public String visitmethodBody(JavaParser.MethodBodyContext ctx) {
			if (ctx.getRuleIndex() == 81) {
				blockVisitor.visitBlock(ctx.block());
			}
			else
				methodbody = ctx.getText();
		return methodbody;
		}
	}
	private static class BlockVisitor extends JavaParserBaseVisitor<String>{
		private String block;
		
		BlockStatementVisitor blockstatementVisitor=new BlockStatementVisitor();
		
		public String visitBlock(JavaParser.BlockContext ctx) {
			if (ctx.getRuleIndex() == 65) {
				blockstatementVisitor.visitBlockStatement(ctx.blockStatement(0));
			}
			else
				block = ctx.getText();
		return block;
		}
	}
	private static class BlockStatementVisitor extends JavaParserBaseVisitor<String>{
		private String blockstatement;
		
		StatementVisitor statementVisitor=new StatementVisitor();
		
		public String visitBlockStatement(JavaParser.BlockStatementContext ctx) {
			if (ctx.getRuleIndex() == 68) {
				statementVisitor.visitStatement(ctx.statement());
			}
			else
				blockstatement = ctx.getText();
		return blockstatement;
		}
	}
	private static class StatementVisitor extends JavaParserBaseVisitor<String>{
		private String statement;
		
		ExpressionVisitor expressionVisitor=new ExpressionVisitor();
		
		public String visitStatement(JavaParser.StatementContext ctx) {
			if (ctx.getRuleIndex() == 83) {
				expressionVisitor.visitExpression(ctx.expression(0));
			}
			else
				statement = ctx.getText();
		return statement;
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
		methodCallVisitor methodcallVisitor = new methodCallVisitor();
		
		public String visitExpression(JavaParser.ExpressionContext ctx) {
			if(ctx.expression(0).getText()=="") {
				return ctx.expression(0).getText();
			}
			if (ctx.getRuleIndex() == 87) {
				primaryVisitor.visitPrimary(ctx.primary());
			}else if(ctx.getRuleIndex()==83) {
				visitExpression(ctx.expression(0));
			}else if(ctx.getRuleIndex()==82) {
				methodcallVisitor.visitmethodCall(ctx.methodCall());
			}else
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
	
}