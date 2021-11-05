package antlr;

import java.util.ArrayList;

import org.antlr.v4.runtime.tree.ParseTree;

public class JavaListener extends JavaParserBaseListener {
	ArrayList<String> queue = new ArrayList<String>();
	ArrayList<Integer> intQueue = new ArrayList<Integer>();
	int iterator = 0;
	
	/*
	@Override
	public void enterCompilationUnit(JavaParser.CompilationUnitContext ctx) {
		System.out.println("enterCompilationUnit");
	}
	
	@Override
	public void exitCompilationUnit(JavaParser.CompilationUnitContext ctx) {
		System.out.println("exitCompilationUnit");
	}*/
	/*
	@Override
	public void enterTypeDeclaration(JavaParser.TypeDeclarationContext ctx) {
		System.out.println("enterTypeDeclaration");
	}
	
	@Override
	public void exitTypeDeclaration(JavaParser.TypeDeclarationContext ctx) {
		System.out.println("exitTypeDeclaration");
	}*/
	
	@Override
	public void enterClassOrInterfaceModifier(JavaParser.ClassOrInterfaceModifierContext ctx) {
		//System.out.println("enterClassOrInterfaceModifier");
		System.out.println(ctx.getText());
	}
	
	@Override
	public void exitClassOrInterfaceModifier(JavaParser.ClassOrInterfaceModifierContext ctx) {
		//System.out.println("exitClassOrInterfaceModifier");
	}
	
	@Override
	public void enterClassOrInterfaceType(JavaParser.ClassOrInterfaceTypeContext ctx) {
		System.out.println(ctx.getText());
	}

	@Override
	public void exitClassOrInterfaceType(JavaParser.ClassOrInterfaceTypeContext ctx) {
		//System.out.println("exitClassOrInterfaceType");
	}

	@Override
	public void enterClassDeclaration(JavaParser.ClassDeclarationContext ctx) { 
		//System.out.println("enterClassDeclaration");
		System.out.println(ctx.CLASS());
		System.out.println(ctx.IDENTIFIER());
	}
	
	@Override
	public void exitClassDeclaration(JavaParser.ClassDeclarationContext ctx) { 
		//System.out.println("exitClassDeclaration");
	}
	/*
	@Override
	public void enterClassBody(JavaParser.ClassBodyContext ctx) {
		System.out.println("enterClassBody");
	}

	@Override
	public void exitClassBody(JavaParser.ClassBodyContext ctx) {
		System.out.println("exitClassBody");
	}*/
	/*
	@Override
	public void enterClassBodyDeclaration(JavaParser.ClassBodyDeclarationContext ctx) {
		System.out.println("enterClassBodyDeclaration");
	}

	@Override
	public void exitClassBodyDeclaration(JavaParser.ClassBodyDeclarationContext ctx) {
		System.out.println("exitClassBodyDeclaration");
	}*/
	/*
	@Override
	public void enterModifier(JavaParser.ModifierContext ctx) { 
		System.out.println("enterModifier");
	}

	@Override
	public void exitModifier(JavaParser.ModifierContext ctx) {
		System.out.println("exitModifier");
	}*/
	/*
	@Override
	public void enterMemberDeclaration(JavaParser.MemberDeclarationContext ctx) { 
		System.out.println("enterMemberDeclaration");
	}
	
	@Override
	public void exitMemberDeclaration(JavaParser.MemberDeclarationContext ctx) {
		System.out.println("exitMemberDeclaration");
	}*/

	@Override 
	public void enterMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
		System.out.println(ctx.IDENTIFIER());
	}

	@Override
	public void exitMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
		//System.out.println("exitMethodDeclaration");
	}

	@Override
	public void enterTypeTypeOrVoid(JavaParser.TypeTypeOrVoidContext ctx) {
		//System.out.println("enterTypeTypeOrVoid");
		System.out.println(ctx.getText());
	}

	@Override
	public void exitTypeTypeOrVoid(JavaParser.TypeTypeOrVoidContext ctx) {
		//System.out.println("exitTypeTypeOrVoid");
	}
	/*
	@Override
	public void enterFormalParameters(JavaParser.FormalParametersContext ctx) {
		System.out.println("enterFormalParameters");
	}

	@Override
	public void exitFormalParameters(JavaParser.FormalParametersContext ctx) {
		System.out.println("exitFormalParameters");
	}*/
	/*
	@Override
	public void enterFormalParameterList(JavaParser.FormalParameterListContext ctx) {
		System.out.println("enterFormalParameterList");
	}

	@Override
	public void exitFormalParameterList(JavaParser.FormalParameterListContext ctx) {
		System.out.println("exitFormalParameterList");
	}*/
	/*
	@Override
	public void enterFormalParameter(JavaParser.FormalParameterContext ctx) {
		System.out.println("enterFormalParameter");
	}

	@Override
	public void exitFormalParameter(JavaParser.FormalParameterContext ctx) {
		System.out.println("exitFormalParameter");
	}*/
	/*
	@Override
	public void enterMethodBody(JavaParser.MethodBodyContext ctx) {
		System.out.println("enterMethodBody");
	}

	@Override
	public void exitMethodBody(JavaParser.MethodBodyContext ctx) {
		System.out.println("exitMethodBody");
	}*/

	@Override
	public void enterBlock(JavaParser.BlockContext ctx) {
		if (ctx.getChild(0).getChildCount() == 0) {
			System.out.println(ctx.getChild(0).getText());
		}
	}

	@Override
	public void exitBlock(JavaParser.BlockContext ctx) {
		if (ctx.getChild(ctx.getChildCount() - 1).getChildCount() == 0) {
			System.out.println(ctx.getChild(0).getText());
		}
	}
	/*
	@Override
	public void enterBlockStatement(JavaParser.BlockStatementContext ctx) {
		System.out.println("enterBlockStatement");
	}*/

	@Override
	public void exitBlockStatement(JavaParser.BlockStatementContext ctx) {
		if (ctx.getChild(ctx.getChildCount() - 1).getChildCount() == 0) {
			System.out.println(ctx.getChild(ctx.getChildCount() - 1).getText());
		}
	}
	/*
	@Override
	public void enterTypeType(JavaParser.TypeTypeContext ctx) {
		//System.out.println("enterTypeType");
	}*/

	@Override
	public void exitTypeType(JavaParser.TypeTypeContext ctx) {
		for (ParseTree p : ctx.children) {
			if (p.getChildCount() == 0) {
				System.out.println(p.getText());
			}
		}
	}
	/*
	@Override
	public void enterVariableDeclaratorId(JavaParser.VariableDeclaratorIdContext ctx) {
		System.out.println(ctx.getText());
	}
	
	@Override
	public void exitVariableDeclaratorId(JavaParser.VariableDeclaratorIdContext ctx) {
		//System.out.println("exitVariableDeclaratorId");
	}*/

	@Override
	public void enterVariableDeclarator(JavaParser.VariableDeclaratorContext ctx) {
		System.out.println(ctx.getChild(0).getText());
		System.out.println(ctx.getChild(1).getText());
	}
	/*
	@Override
	public void exitVariableDeclarator(JavaParser.VariableDeclaratorContext ctx) { 
		System.out.println("exitVariableDeclarator");
	}*/
	/*
	@Override
	public void enterLocalVariableDeclaration(JavaParser.LocalVariableDeclarationContext ctx) {
		System.out.println("enterLocalVariableDeclaration");
	}

	@Override
	public void exitLocalVariableDeclaration(JavaParser.LocalVariableDeclarationContext ctx) {
		System.out.println("exitLocalVariableDeclaration");
	}*/

	@Override
	public void enterPrimitiveType(JavaParser.PrimitiveTypeContext ctx) {
		System.out.println(ctx.getText());
	}
	/*
	@Override
	public void exitPrimitiveType(JavaParser.PrimitiveTypeContext ctx) {
		System.out.println("exitPrimitiveType");
	}*/
	/*
	@Override
	public void enterStatement(JavaParser.StatementContext ctx) {
		System.out.println("enterStatement");
	}*/

	@Override
	public void exitStatement(JavaParser.StatementContext ctx) {
		System.out.println("exitStatement");
	}
	
	@Override
	public void enterExpression(JavaParser.ExpressionContext ctx) {
		iterator++;
		for (ParseTree p : ctx.children) {
			if (p.getChildCount() == 0) {
				queue.add(p.getText());
				intQueue.add(iterator);
			}
		}
	}

	@Override
	public void exitExpression(JavaParser.ExpressionContext ctx) {
		iterator--;
		if (intQueue.lastIndexOf(iterator) != -1) {
			if (!queue.isEmpty()) {
				System.out.println(queue.get(0));
				queue.remove(0);
				intQueue.remove(intQueue.size() - 1);
			}
		}
	}
	
	@Override
	public void enterPrimary(JavaParser.PrimaryContext ctx) {
		if (ctx.getChild(0).getChildCount() == 0) {
			System.out.println(ctx.getChild(0).getText());
		}
	}

	@Override
	public void exitPrimary(JavaParser.PrimaryContext ctx) {
		if (ctx.getChildCount() > 1 && ctx.getChild(ctx.getChildCount() - 1).getChildCount() == 0) {
			System.out.println(ctx.getChild(ctx.getChildCount() - 1).getText());
		}
	}

	@Override
	public void enterMethodCall(JavaParser.MethodCallContext ctx) {
		System.out.println("enterMethodCall");
	}

	@Override
	public void exitMethodCall(JavaParser.MethodCallContext ctx) {
		System.out.println("exitMethodCall");
	}

	@Override
	public void enterIntegerLiteral(JavaParser.IntegerLiteralContext ctx) {
		System.out.println(ctx.getText());
	}
	/*
	@Override
	public void exitIntegerLiteral(JavaParser.IntegerLiteralContext ctx) {
		System.out.println("exitIntegerLiteral");
	}*/

	@Override
	public void enterFloatLiteral(JavaParser.FloatLiteralContext ctx) {
		System.out.println(ctx.getText());
	}
	/*
	@Override
	public void exitFloatLiteral(JavaParser.FloatLiteralContext ctx) { 
		System.out.println("exitFloatLiteral");
	}*/

}
