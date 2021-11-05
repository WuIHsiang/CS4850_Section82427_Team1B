package antlr;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import javafx.scene.control.TextArea;
//Currently on line 
public class JavaTranslator extends antlr.JavaParserBaseVisitor<String> {	
	/*public TypeVisitor parse(JavaParser parser) {
		TypeVisitor typeVisitor = new TypeVisitor();
		Type traverseResult = typeVisitor.visit(parser.compilationUnit());
		return traverseResult;
	}*/
	/*Classes left to implement
		RULE_enumBodyDeclarations = 14, RULE_genericMethodDeclaration = 23, RULE_genericConstructorDeclaration = 24, 
		RULE_constDeclaration = 29, RULE_constantDeclarator = 30,  RULE_genericInterfaceMethodDeclaration = 33,  
		RULE_typeArgument = 40, RULE_qualifiedNameList = 41, RULE_lastFormalParameter = 45, 
		RULE_qualifiedName = 46,  RULE_altAnnotationQualifiedName = 50, RULE_annotation = 51, 
		RULE_elementValuePairs = 52, RULE_elementValuePair = 53, RULE_elementValue = 54, 
		RULE_elementValueArrayInitializer = 55, RULE_annotationTypeDeclaration = 56, RULE_annotationTypeBody = 57, 
		RULE_annotationTypeElementDeclaration = 58, RULE_annotationTypeElementRest = 59, RULE_annotationMethodOrConstantRest = 60, 
		RULE_annotationMethodRest = 61, RULE_annotationConstantRest = 62, RULE_defaultValue = 63, 
		RULE_localTypeDeclaration = 67,  RULE_catchClause = 69, RULE_catchType = 70, 
		RULE_finallyBlock = 71, RULE_resourceSpecification = 72, RULE_resources = 73, 
		RULE_resource = 74, RULE_switchBlockStatementGroup = 75, RULE_switchLabel = 76, 
		RULE_forControl = 77, RULE_forInit = 78, RULE_enhancedForControl = 79, 
		RULE_parExpression = 80, RULE_lambdaExpression = 84, RULE_lambdaParameters = 85, 
		RULE_lambdaBody = 86,  RULE_classType = 88, RULE_innerCreator = 91, 
		RULE_arrayCreatorRest = 92,  RULE_explicitGenericInvocation = 94, RULE_typeArgumentsOrDiamond = 95, 
		RULE_nonWildcardTypeArgumentsOrDiamond = 96, RULE_nonWildcardTypeArguments = 97, RULE_typeList = 98,   
		RULE_typeArguments = 101, RULE_superSuffix = 102, RULE_explicitGenericInvocationSuffix = 103
	 */
		@Override
		public String visitCompilationUnit(JavaParser.CompilationUnitContext ctx) {
			
			System.out.println("A");
			String compilationUnit;
			
			typeDeclarationVisitor TypeDeclarationVisitor=new typeDeclarationVisitor();
			importDeclarationVisitor ImportDeclarationVisitor=new importDeclarationVisitor();
			packageDeclarationVisitor PackageDeclarationVisitor=new packageDeclarationVisitor();
			if (ctx.getRuleIndex() == 3) {
				TypeDeclarationVisitor.visittypeDeclaration(ctx.typeDeclaration(0));
			}if (ctx.getRuleIndex() == 2) {
				ImportDeclarationVisitor.visitimportDeclaration(ctx.importDeclaration(0));
			}if (ctx.getRuleIndex() == 1) {
				PackageDeclarationVisitor.visitpackageDeclaration(ctx.packageDeclaration());
			}
				compilationUnit = ctx.getText();
		return compilationUnit;
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
		EnumDeclarationVisitor enumDeclarationVisitor=new EnumDeclarationVisitor();
		
		public String visittypeDeclaration(JavaParser.TypeDeclarationContext ctx) {
			if (ctx.getRuleIndex() == 7) {
				ClassDeclarationVisitor.visitclassDeclaration(ctx.classDeclaration());
			}if (ctx.getRuleIndex() == 15) {
				InterfaceDeclarationVisitor.visitinterfaceDeclaration(ctx.interfaceDeclaration());
			}if (ctx.getRuleIndex() == 11) {
				enumDeclarationVisitor.visitEnumDeclaration(ctx.enumDeclaration());
			}
			
		typedeclaration = ctx.getText();
		return typedeclaration;
		}
	}
	
	private static class EnumDeclarationVisitor extends JavaParserBaseVisitor<String>{
		private String enumdeclaration;
		EnumConstantsVisitor enumConstantsVisitor=new EnumConstantsVisitor();
		
		
		public String visitEnumDeclaration(JavaParser.EnumDeclarationContext ctx) {
			if(ctx.getRuleIndex()==12) {
				enumConstantsVisitor.visitEnumConstants(ctx.enumConstants());
			} 
				enumdeclaration = ctx.getText();
		return enumdeclaration;
		}
	}
	private static class EnumConstantsVisitor extends JavaParserBaseVisitor<String>{
		private String enumconstants;
		EnumConstantVisitor enumConstantVisitor=new EnumConstantVisitor();
		
		
		public String visitEnumConstants(JavaParser.EnumConstantsContext ctx) {
			if(ctx.getRuleIndex()==13) {
				enumConstantVisitor.visitEnumConstant(ctx.enumConstant(0));
			} 
				enumconstants = ctx.getText();
		return enumconstants;
		}
	}
	private static class EnumConstantVisitor extends JavaParserBaseVisitor<String>{
		private String enumconstant;
		
		public String visitEnumConstant(JavaParser.EnumConstantContext ctx) {
			
		enumconstant = ctx.getText();
		return enumconstant;
		}
	}
	private static class classDeclarationVisitor extends JavaParserBaseVisitor<String>{
		private String classdeclaration;
		typeParametersVisitor TypeParametersVisitor=new typeParametersVisitor();
		
		ClassBodyVisitor ClassBodyVisitor=new ClassBodyVisitor();
		public String visitclassDeclaration(JavaParser.ClassDeclarationContext ctx) {
			if (ctx.getRuleIndex() == 16) {
				ClassBodyVisitor.visitClassBody(ctx.classBody());
			}if (ctx.getRuleIndex() == 8) {
				TypeParametersVisitor.visittypeParameters(ctx.typeParameters());
			}
			
				classdeclaration = ctx.getText();
		return classdeclaration;
		}
	}
	private static class FieldDeclarationVisitor extends JavaParserBaseVisitor<String>{
		private String fielddeclaration;
		typeTypeVisitor TypeTypeVisitor=new typeTypeVisitor();
		public String visitFieldDeclaration(JavaParser.FieldDeclarationContext ctx) {
			if(ctx.getRuleIndex()==36) {
				TypeTypeVisitor.visitTypeType(ctx.typeType());
			}
			
			fielddeclaration = ctx.getText();
			return fielddeclaration;
		}
	}
	private static class ConstructorDeclarationVisitor extends JavaParserBaseVisitor<String>{
		private String constructordeclaration;
		FormalParametersVisitor formalParameters=new FormalParametersVisitor();
		public String visitConstructorDeclaration(JavaParser.ConstructorDeclarationContext ctx) {
			if (ctx.getRuleIndex() == 42) {
				formalParameters.visitFormalParameters(ctx.formalParameters());
			}
				constructordeclaration = ctx.getText();
				return constructordeclaration;
		}
	}
	private static class ClassBodyVisitor extends JavaParserBaseVisitor<String>{
		private String classbody;
		
		ClassBodyDeclarationVisitor classBodyDeclarationVisitor=new ClassBodyDeclarationVisitor();
		
		public String visitClassBody(JavaParser.ClassBodyContext ctx) {
			if (ctx.getRuleIndex() == 18) {
				classBodyDeclarationVisitor.visitClassBodyDeclaration(ctx.classBodyDeclaration(0));
			}
			
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
			}if (ctx.getRuleIndex() == 19) {
				memberdeclarationVisitor.visitmemberDeclaration(ctx.memberDeclaration());
			}
			
				classbodydeclaration = ctx.getText();
		return classbodydeclaration;
		}
	}
	private static class typeParametersVisitor extends JavaParserBaseVisitor<String>{
		private String typeparameters;
		typeParameterVisitor TypeParameterVisitor=new typeParameterVisitor();
		
		public String visittypeParameters(JavaParser.TypeParametersContext ctx) {
			if(ctx.getRuleIndex()==9) {
				TypeParameterVisitor.visittypeParameter(ctx.typeParameter(0));
			}
		typeparameters = ctx.getText();
		return typeparameters;
		}
	}
	private static class typeParameterVisitor extends JavaParserBaseVisitor<String>{
		private String typeparameter;
		typeBoundVisitor TypeBoundVisitor=new typeBoundVisitor();
		
		public String visittypeParameter(JavaParser.TypeParameterContext ctx) {
			if(ctx.getRuleIndex()==10) {
				TypeBoundVisitor.visittypeBound(ctx.typeBound());
			}
		typeparameter = ctx.getText();
		return typeparameter;
		}
	}
	private static class typeBoundVisitor extends JavaParserBaseVisitor<String>{
		private String typebound;
		
		public String visittypeBound(JavaParser.TypeBoundContext ctx) {
			
		typebound = ctx.getText();
		return typebound;
		}
	}
	private static class interfaceDeclarationVisitor extends JavaParserBaseVisitor<String>{
		private String classdeclaration;
		
		InterfaceBodyVisitor interfaceBodyVisitor=new InterfaceBodyVisitor();
		
		
		public String visitinterfaceDeclaration(JavaParser.InterfaceDeclarationContext ctx) {
			if (ctx.getRuleIndex() == 17) {
				interfaceBodyVisitor.visitInterfaceBody(ctx.interfaceBody());
			}
			
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
			}if(ctx.getRuleIndex() == 28) {
				InterfaceMemberDeclarationVisitor.visitinterfaceMemberDeclaration(ctx.interfaceMemberDeclaration());
			}
			
				interfacebodydeclaration = ctx.getText();
		return interfacebodydeclaration;
		}
	}
	
	private static class ModifierVisitor extends JavaParserBaseVisitor<String>{
		private String modifier;
		
		ClassOrInterfaceModifierVisitor classorinterfaceVisitor=new ClassOrInterfaceModifierVisitor();
		
		
		public String visitModifier(JavaParser.ModifierContext ctx) {
			if (ctx.getRuleIndex() == 5) {
				classorinterfaceVisitor.visitClassOrInterfaceModifier(ctx.classOrInterfaceModifier());
			}
			
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
	
	private static class methodCallVisitor extends JavaParserBaseVisitor<String>{
		private String methodcall;
		
		ExpressionListVisitor expressionListVisitor=new ExpressionListVisitor();
		
		public String visitmethodCall(JavaParser.MethodCallContext ctx) {
			if (ctx.getRuleIndex() == 81) {
				expressionListVisitor.visitExpressionList(ctx.expressionList());
			}
			
				methodcall = ctx.getText();
		return methodcall;
		}
	}
	private static class memberDeclarationVisitor extends JavaParserBaseVisitor<String>{
		private String memberdeclaration;
		ConstructorDeclarationVisitor constructorDeclarationVisitor=new ConstructorDeclarationVisitor();
		FieldDeclarationVisitor fieldDeclarationVisitor=new FieldDeclarationVisitor();
		methodDeclarationVisitor methoddeclarationVisitor=new methodDeclarationVisitor();
		
		public String visitmemberDeclaration(JavaParser.MemberDeclarationContext ctx) {
			if (ctx.getRuleIndex() == 20) {
				methoddeclarationVisitor.visitmethodDeclaration(ctx.methodDeclaration());
			}if(ctx.getRuleIndex() == 25) {
				constructorDeclarationVisitor.visitConstructorDeclaration(ctx.constructorDeclaration());
			}if(ctx.getRuleIndex() == 26) {
				fieldDeclarationVisitor.visitFieldDeclaration(ctx.fieldDeclaration());
			}
				memberdeclaration = ctx.getText();
		return memberdeclaration;
		}
	}
	private static class methodDeclarationVisitor extends JavaParserBaseVisitor<String>{
		private String methoddeclaration;
		
		methodBodyVisitor methodbodyVisitor=new methodBodyVisitor();
		typeTypeorVoidVisitor typeTypeorVoidVisitor=new typeTypeorVoidVisitor();
		FormalParametersVisitor FormalParametersVisitor=new FormalParametersVisitor();
		
		public String visitmethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
			if (ctx.getRuleIndex() == 21) {
				methodbodyVisitor.visitmethodBody(ctx.methodBody());
			}if (ctx.getRuleIndex() == 22) {
				typeTypeorVoidVisitor.visittypeTypeorVoid(ctx.typeTypeOrVoid());
			}if (ctx.getRuleIndex() == 42) {
				FormalParametersVisitor.visitFormalParameters(ctx.formalParameters());
			}
				methoddeclaration = ctx.getText();
		return methoddeclaration;
		}
	}
	private static class GenericMethodDeclarationVisitor extends JavaParserBaseVisitor<String>{
		private String genericmethoddeclaration;
		
		public String visitGenericMethodDeclaration(JavaParser.GenericMethodDeclarationContext ctx) {
			
				genericmethoddeclaration = ctx.getText();
		return genericmethoddeclaration;
		}
	}
	private static class InterfaceMethodModifierVisitor extends JavaParserBaseVisitor<String>{
		private String interfacemethodmodifierdeclaration;
		
		public String visitInterfaceMethodModifier(JavaParser.InterfaceMethodModifierContext ctx) {
			interfacemethodmodifierdeclaration = ctx.getText();
			return interfacemethodmodifierdeclaration;
		}
	}
	private static class interfaceMemberDeclarationVisitor extends JavaParserBaseVisitor<String>{
		private String interfacememberdeclaration;
		
		InterfaceMethodDeclarationVisitor interfacemethoddeclarationVisitor=new InterfaceMethodDeclarationVisitor();
		
		public String visitinterfaceMemberDeclaration(JavaParser.InterfaceMemberDeclarationContext ctx) {
			if (ctx.getRuleIndex() == 31) {
				interfacemethoddeclarationVisitor.visitinterfaceMethodDeclaration(ctx.interfaceMethodDeclaration());
			}
			
				interfacememberdeclaration = ctx.getText();
		return interfacememberdeclaration;
		}
	}
	private static class InterfaceMethodDeclarationVisitor extends JavaParserBaseVisitor<String>{
		private String interfacemethoddeclaration;
		
		methodBodyVisitor methodbodyVisitor=new methodBodyVisitor();
		typeTypeorVoidVisitor typeTypeorVoidVisitor=new typeTypeorVoidVisitor();
		FormalParametersVisitor FormalParametersVisitor=new FormalParametersVisitor();
		InterfaceMethodModifierVisitor interfaceMethodModifierVisitor=new InterfaceMethodModifierVisitor();
		public String visitinterfaceMethodDeclaration(JavaParser.InterfaceMethodDeclarationContext ctx) {
			if (ctx.getRuleIndex() == 21) {
				methodbodyVisitor.visitmethodBody(ctx.methodBody());
			}if (ctx.getRuleIndex() == 22) {
				typeTypeorVoidVisitor.visittypeTypeorVoid(ctx.typeTypeOrVoid());
			}if (ctx.getRuleIndex() == 42) {
				FormalParametersVisitor.visitFormalParameters(ctx.formalParameters());
			}if (ctx.getRuleIndex() == 32) {
				interfaceMethodModifierVisitor.visitInterfaceMethodModifier(ctx.interfaceMethodModifier(0));
			}
				interfacemethoddeclaration = ctx.getText();
		return interfacemethoddeclaration;
		}
	}
	
	private static class LocalVariableDeclaration extends JavaParserBaseVisitor<String>{
		private String localvariabledeclaration;
		
		typeTypeVisitor TypeTypeVisitor=new typeTypeVisitor();
		VariableDeclaratorsVisitor variableDeclaratorsVisitor=new VariableDeclaratorsVisitor();
		
		public String visitLocalVariableDeclaration(JavaParser.LocalVariableDeclarationContext ctx) {
			if(ctx.getRuleIndex()==100) {
				TypeTypeVisitor.visitTypeType(ctx.typeType());
			}if(ctx.getRuleIndex()==34) {
				variableDeclaratorsVisitor.visitVariableDeclarators(ctx.variableDeclarators());
			}
			
				localvariabledeclaration= ctx.getText();
				return localvariabledeclaration;
		}
	}
	private static class FormalParametersVisitor extends JavaParserBaseVisitor<String>{
		private String formalparameters;
		FormalParameterListVisitor formalParameterListVisitor=new FormalParameterListVisitor();
		public String visitFormalParameters(JavaParser.FormalParametersContext ctx) {
			if(ctx.getRuleIndex()==44) {
				formalParameterListVisitor.visitFormalParameterList(ctx.formalParameterList());
			}
			
			formalparameters = ctx.getText();
			return formalparameters;
		}
	}
	private static class FormalParameterListVisitor extends JavaParserBaseVisitor<String>{
		private String formalparameterlist;
		FormalParameterVisitor formalParameterVisitor=new FormalParameterVisitor();
		public String visitFormalParameterList(JavaParser.FormalParameterListContext ctx) {
			if(ctx.getRuleIndex()==44) {
				formalParameterVisitor.visitFormalParameter(ctx.formalParameter(0));
			}
			
			formalparameterlist = ctx.getText();
			return formalparameterlist;
		}
	}
	private static class FormalParameterVisitor extends JavaParserBaseVisitor<String>{
		private String formalparameter;
		typeTypeVisitor TypeTypeVisitor=new typeTypeVisitor();
		public String visitFormalParameter(JavaParser.FormalParameterContext ctx) {
			if(ctx.getRuleIndex()==36) {
				TypeTypeVisitor.visitTypeType(ctx.typeType());
			}
			
			formalparameter = ctx.getText();
			return formalparameter;
		}
	}
	private static class typeTypeVisitor extends JavaParserBaseVisitor<String>{
		private String typetype;
		
		classOrInterfaceTypeVisitor ClassOrInterfaceTypeVisitor=new classOrInterfaceTypeVisitor();
		
		PrimitiveTypeVisitor primitiveTypeVisitor=new PrimitiveTypeVisitor();
		
		public String visitTypeType(JavaParser.TypeTypeContext ctx) {
			if (ctx.getRuleIndex() == 39) {
				ClassOrInterfaceTypeVisitor.visitclassOrInterfaceType(ctx.classOrInterfaceType());
			}if (ctx.getRuleIndex()==100) {
				primitiveTypeVisitor.visitPrimitiveType(ctx.primitiveType());
			}
			
				typetype = ctx.getText();
		return typetype;
		}
	}
	
	private static class PrimitiveTypeVisitor extends JavaParserBaseVisitor<String>{
		private String primitivetype;
		
		
		public String visitPrimitiveType(JavaParser.PrimitiveTypeContext ctx) {
			primitivetype = ctx.getText();
			return primitivetype;
		}
	}
	private static class ClassTypeVisitor extends JavaParserBaseVisitor<String>{
		private String classtype;
		
		
		public String visitClassType(JavaParser.ClassTypeContext ctx) {
			classtype = ctx.getText();
			return classtype;
		}
	}
	private static class VariableDeclaratorsVisitor extends JavaParserBaseVisitor<String>{
		private String variabledeclarators;
		
		VariableDeclaratorVisitor variableDeclaratorVisitor=new VariableDeclaratorVisitor();
		
		public String visitVariableDeclarators(JavaParser.VariableDeclaratorsContext ctx) {
			if(ctx.getRuleIndex()==35) {
				variableDeclaratorVisitor.visitVariableDeclarator(ctx.variableDeclarator(0));
			}
			variabledeclarators = ctx.getText();
			return variabledeclarators;
		}
	}
	private static class VariableDeclaratorVisitor extends JavaParserBaseVisitor<String>{
		private String variabledeclarator;
		VariableDeclaratorIdVisitor variableDeclaratorIdVisitor=new VariableDeclaratorIdVisitor();
		VariableInitializerVisitor variableInitializerVisitor=new VariableInitializerVisitor();
		public String visitVariableDeclarator(JavaParser.VariableDeclaratorContext ctx) {
			if(ctx.getRuleIndex()==36) {
				variableDeclaratorIdVisitor.visitVariableDeclaratorId(ctx.variableDeclaratorId());
			}
			if(ctx.getRuleIndex()==37) {
				variableInitializerVisitor.visitVariableInitializer(ctx.variableInitializer());
			}
			
			variabledeclarator = ctx.getText();
			return variabledeclarator;
		}
	}
	private static class VariableDeclaratorIdVisitor extends JavaParserBaseVisitor<String>{
		private String variabledeclaratorid;
		public String visitVariableDeclaratorId(JavaParser.VariableDeclaratorIdContext ctx) {
			variabledeclaratorid = ctx.getText();
			return variabledeclaratorid;
		}
	}
	private static class VariableModifierVisitor extends JavaParserBaseVisitor<String>{
		private String variablemodifier;
		
		public String visitVariableModifier(JavaParser.VariableModifierContext ctx) {
			variablemodifier = ctx.getText();
			return variablemodifier;
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
			
				block = ctx.getText();
		return block;
		}
	}
	private static class BlockStatementVisitor extends JavaParserBaseVisitor<String>{
		private String blockstatement;
		
		StatementVisitor statementVisitor=new StatementVisitor();
		LocalVariableDeclaration localVariableDeclaration=new LocalVariableDeclaration();
		
		public String visitBlockStatement(JavaParser.BlockStatementContext ctx) {
			if (ctx.getRuleIndex() == 68) {
				statementVisitor.visitStatement(ctx.statement());
			}if (ctx.getRuleIndex() == 66) {
				localVariableDeclaration.visitLocalVariableDeclaration(ctx.localVariableDeclaration());
			}
			
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
			
				expressionlist = ctx.getText();
		return expressionlist;
		}
	}
	
	private static class ExpressionVisitor extends JavaParserBaseVisitor<String>{
		private String expression;
		
		PrimaryVisitor primaryVisitor = new PrimaryVisitor();
		methodCallVisitor methodcallVisitor = new methodCallVisitor();
		CreatorVisitor creatorVisitor=new CreatorVisitor();
		
		public String visitExpression(JavaParser.ExpressionContext ctx) {
			if(ctx.expression(0).getText()=="") {
				return ctx.expression(0).getText();
			}if (ctx.getRuleIndex() == 87) {
				primaryVisitor.visitPrimary(ctx.primary());
			}if(ctx.getRuleIndex()==83) {
				visitExpression(ctx.expression(0));
			}if(ctx.getRuleIndex()==82) {
				methodcallVisitor.visitmethodCall(ctx.methodCall());
			}if(ctx.getRuleIndex()==89) {
				creatorVisitor.visitCreator(ctx.creator());
			}
				expression = ctx.getText();
		return expression;
		}
	}
	private static class ArrayInitializerVisitor extends JavaParserBaseVisitor<String>{
		private String arrayinitializaer;
		
		VariableInitializerVisitor variableInitializerVisitor=new VariableInitializerVisitor();
		
		public String visitArrayInitializer(JavaParser.ArrayInitializerContext ctx) {
			if (ctx.getRuleIndex() == 37) {
				variableInitializerVisitor.visitVariableInitializer(ctx.variableInitializer(0));
			}
			
				arrayinitializaer = ctx.getText();
		return arrayinitializaer;
		}
	}
	private static class VariableInitializerVisitor extends JavaParserBaseVisitor<String>{
		private String variableinitializaer;
		
		ExpressionVisitor expressionVisitor=new ExpressionVisitor();
		ArrayInitializerVisitor arrayInitializerVisitor=new ArrayInitializerVisitor();
		
		public String visitVariableInitializer(JavaParser.VariableInitializerContext ctx) {
			if (ctx.getRuleIndex() == 83) {
				expressionVisitor.visitExpression(ctx.expression());
			}if(ctx.getRuleIndex() == 38) {
				arrayInitializerVisitor.visitArrayInitializer(ctx.arrayInitializer());
			}
			
				variableinitializaer = ctx.getText();
		return variableinitializaer;
		}
	}
	public static class CreatorVisitor extends JavaParserBaseVisitor<String>{
		 private String creator;
		 ClassCreatorRestVisitor classCreatorRestVisitor=new ClassCreatorRestVisitor();
		 CreatedNameVisitor createdNameVisitor=new CreatedNameVisitor();
		 
		 public String visitCreator(JavaParser.CreatorContext ctx) {
			 if(ctx.getRuleIndex()==93) {
				 classCreatorRestVisitor.visitClassCreatorRest(ctx.classCreatorRest());
			 }if(ctx.getRuleIndex()==90) {
				 createdNameVisitor.visitCreatedName(ctx.createdName());
			 }
			 creator = ctx.getText();
			 return creator;
		 }
	}
	public static class CreatedNameVisitor extends JavaParserBaseVisitor<String>{
		 private String createdname;
		 
		 public String visitCreator(JavaParser.CreatorContext ctx) {
			 
			 createdname = ctx.getText();
			 return createdname;
		 }
	}
	public static class ClassCreatorRestVisitor extends JavaParserBaseVisitor<String>{
		 private String classcreatorrest;
		 ArgumentsVisitor argumentsVisitor=new ArgumentsVisitor();
		 
		 public String visitClassCreatorRest(JavaParser.ClassCreatorRestContext ctx) {
			 if(ctx.getRuleIndex()==104) {
				 argumentsVisitor.visitArguments(ctx.arguments());
			 }
			 classcreatorrest = ctx.getText();
			 return classcreatorrest;
		 }
	}
	public static class ArgumentsVisitor extends JavaParserBaseVisitor<String>{
		 private String arguments;
		 
		 public String visitArguments(JavaParser.ArgumentsContext ctx) {
			 arguments = ctx.getText();
			 return arguments;
		 }
	}
	private static class PrimaryVisitor extends JavaParserBaseVisitor<String>{
		private String primary;
		
		LiteralVisitor literalVisitor = new LiteralVisitor();
		
		public String visitPrimary(JavaParser.PrimaryContext ctx) {
			if (ctx.getRuleIndex() == 47) {
				literalVisitor.visitLiteral(ctx.literal());
			}
			
				primary = ctx.getText();
		return primary;
		}
	}
	
	public static class LiteralVisitor extends JavaParserBaseVisitor<String>{
		 private String literal;
		 
		 integerLiteralVisitor IntegerLiteralVisitor=new integerLiteralVisitor();
		 floatLiteralVisitor FloatLiteralVisitor=new floatLiteralVisitor();
		 public String visitLiteral(JavaParser.LiteralContext ctx) {
			 if(ctx.getRuleIndex()==48) {
				 IntegerLiteralVisitor.visitIntegerLiteral(ctx.integerLiteral());
			 }if(ctx.getRuleIndex()==49) {
				 FloatLiteralVisitor.visitFloatLiteral(ctx.floatLiteral());
			 } 
				 literal = ctx.getText();
				 return literal;
		}
	}
	public static class integerLiteralVisitor extends JavaParserBaseVisitor<String>{
		 private String integerliteral;
		 
		 public String visitIntegerLiteral(JavaParser.IntegerLiteralContext ctx) {
			 integerliteral = ctx.getText();
			 return integerliteral;
		 }
	}
	public static class floatLiteralVisitor extends JavaParserBaseVisitor<String>{
		 private String floatliteral;
		 
		 public String visitFloatLiteral(JavaParser.FloatLiteralContext ctx) {
			 floatliteral = ctx.getText();
			 return floatliteral;
		 }
	}
	
}