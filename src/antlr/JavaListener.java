package antlr;

import java.util.ArrayList;

import org.antlr.v4.runtime.tree.ParseTree;

public class JavaListener extends JavaParserBaseListener {
	public ArrayList<String> tokens = new ArrayList<String>();
	
	/*
	@Override
	public void enterCompilationUnit(JavaParser.CompilationUnitContext ctx) {
	}
	
	@Override
	public void exitCompilationUnit(JavaParser.CompilationUnitContext ctx) {
	}*/
	/*
	@Override
	public void enterTypeDeclaration(JavaParser.TypeDeclarationContext ctx) {
	}
	
	@Override
	public void exitTypeDeclaration(JavaParser.TypeDeclarationContext ctx) {
	}*/
	
	@Override
	public void enterClassOrInterfaceModifier(JavaParser.ClassOrInterfaceModifierContext ctx) {
		tokens.add(ctx.getText());
		
	}
	
	@Override
	public void exitClassOrInterfaceModifier(JavaParser.ClassOrInterfaceModifierContext ctx) {
	}
	
	@Override
	public void enterClassOrInterfaceType(JavaParser.ClassOrInterfaceTypeContext ctx) {
		tokens.add(ctx.getText());
	}

	@Override
	public void exitClassOrInterfaceType(JavaParser.ClassOrInterfaceTypeContext ctx) {
	}

	@Override
	public void enterClassDeclaration(JavaParser.ClassDeclarationContext ctx) { 
		tokens.add(ctx.CLASS().toString());
		tokens.add(ctx.IDENTIFIER().toString());
	}
	
	@Override
	public void exitClassDeclaration(JavaParser.ClassDeclarationContext ctx) { 
	}
	
	@Override
	public void enterClassBody(JavaParser.ClassBodyContext ctx) {
		if (ctx.getChild(0).getChildCount() == 0) {
			tokens.add(ctx.getChild(0).getText());
		}
	}

	@Override
	public void exitClassBody(JavaParser.ClassBodyContext ctx) {
		if (ctx.getChild(ctx.getChildCount() - 1).getChildCount() == 0) {
			tokens.add(ctx.getChild(ctx.getChildCount() - 1).getText());
		}
	}
	/*
	@Override
	public void enterClassBodyDeclaration(JavaParser.ClassBodyDeclarationContext ctx) {
	}

	@Override
	public void exitClassBodyDeclaration(JavaParser.ClassBodyDeclarationContext ctx) {
	}*/
	/*
	@Override
	public void enterModifier(JavaParser.ModifierContext ctx) { 
	}

	@Override
	public void exitModifier(JavaParser.ModifierContext ctx) {
	}*/
	/*
	@Override
	public void enterMemberDeclaration(JavaParser.MemberDeclarationContext ctx) { 
	}
	
	@Override
	public void exitMemberDeclaration(JavaParser.MemberDeclarationContext ctx) {
	}*/

	@Override 
	public void enterMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
		if (ctx.getChild(0).getChildCount() == 0) {
			tokens.add(ctx.getChild(0).getText());
			if (ctx.getChild(1).getChildCount() == 0) {
				tokens.add(ctx.getChild(1).getText());
			}
		}
	}

	@Override
	public void exitMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
	}

	@Override
	public void enterTypeTypeOrVoid(JavaParser.TypeTypeOrVoidContext ctx) {
		if (ctx.getChild(0).getChildCount() == 0) {
			tokens.add(ctx.getText());
		}
	}
	
	@Override
	public void exitTypeTypeOrVoid(JavaParser.TypeTypeOrVoidContext ctx) {
		int index = 0;
        for (int i = 0; i < ctx.getParent().children.size(); ++i) {
            if (ctx.getParent().getChild(i).getChildCount() == 0) {
            	index = i;
                break;
            }
        }
        tokens.add(ctx.getParent().getChild(index).getText());
	}
	
	@Override
	public void enterFormalParameters(JavaParser.FormalParametersContext ctx) {
		if (ctx.getChild(0).getChildCount() == 0) {
			tokens.add(ctx.getChild(0).getText());
		}
	}

	@Override
	public void exitFormalParameters(JavaParser.FormalParametersContext ctx) {
		if (ctx.getChild(2).getChildCount() == 0) {
			tokens.add(ctx.getChild(2).getText());
		}
	}
	/*
	@Override
	public void enterFormalParameterList(JavaParser.FormalParameterListContext ctx) {
	}

	@Override
	public void exitFormalParameterList(JavaParser.FormalParameterListContext ctx) {
	}*/
	/*
	@Override
	public void enterFormalParameter(JavaParser.FormalParameterContext ctx) {
	}*/

	@Override
	public void exitFormalParameter(JavaParser.FormalParameterContext ctx) {
		if (ctx.getChild(ctx.getChildCount() - 1).getChild(0).getChildCount() == 0) {
			tokens.add(ctx.getChild(ctx.getChildCount() - 1).getChild(0).getText());
		}
	}
	/*
	@Override
	public void enterMethodBody(JavaParser.MethodBodyContext ctx) {
	}

	@Override
	public void exitMethodBody(JavaParser.MethodBodyContext ctx) {
	}*/

	@Override
	public void enterBlock(JavaParser.BlockContext ctx) {
		if (ctx.getChild(0).getChildCount() == 0) {
			tokens.add(ctx.getChild(0).getText());
		}
	}

	@Override
	public void exitBlock(JavaParser.BlockContext ctx) {
		if (ctx.getChild(ctx.getChildCount() - 1).getChildCount() == 0) {
			tokens.add(ctx.getChild(ctx.getChildCount() - 1).getText());
		}
	}
	
	@Override
	public void enterBlockStatement(JavaParser.BlockStatementContext ctx) {
	}

	@Override
	public void exitBlockStatement(JavaParser.BlockStatementContext ctx) {
		if (ctx.getChild(ctx.getChildCount() - 1).getChildCount() == 0) {
			tokens.add(ctx.getChild(ctx.getChildCount() - 1).getText());
		}
	}
	/*
	@Override
	public void enterTypeType(JavaParser.TypeTypeContext ctx) {
	}*/

	@Override
	public void exitTypeType(JavaParser.TypeTypeContext ctx) {
		for (ParseTree p : ctx.children) {
			if (p.getChildCount() == 0) {
				tokens.add(p.getText());
			}
		}
		
		var parent = ctx.getParent();
		if (parent.getRuleIndex() == 79)
			tokens.add(parent.getChild(1).getText() + " in ");
	}
	
	@Override
	public void enterVariableDeclaratorId(JavaParser.VariableDeclaratorIdContext ctx) {
		//tokens.add(ctx.getText());
	}
	/*
	@Override
	public void exitVariableDeclaratorId(JavaParser.VariableDeclaratorIdContext ctx) {
	}*/

	@Override
	public void enterVariableDeclarator(JavaParser.VariableDeclaratorContext ctx) {
		tokens.add(ctx.getChild(0).getText());
		tokens.add(ctx.getChild(1).getText());
	}
	/*
	@Override
	public void exitVariableDeclarator(JavaParser.VariableDeclaratorContext ctx) { 
	}*/
	/*
	@Override
	public void enterLocalVariableDeclaration(JavaParser.LocalVariableDeclarationContext ctx) {
	}

	@Override
	public void exitLocalVariableDeclaration(JavaParser.LocalVariableDeclarationContext ctx) {
	}*/

	@Override
	public void enterPrimitiveType(JavaParser.PrimitiveTypeContext ctx) {
		tokens.add(ctx.getText());
	}
	/*
	@Override
	public void exitPrimitiveType(JavaParser.PrimitiveTypeContext ctx) {
	}*/
	
	@Override
	public void enterStatement(JavaParser.StatementContext ctx) {
        var parent = ctx.getParent();
       
        if (parent.getChildCount() > 2) {
        	if (parent.getChild(parent.getChildCount() - 1) == ctx && parent.getChild(parent.getChildCount() - 2).getText().contentEquals("else")) {
        		tokens.add("else");
        	}
        }
        
        if (ctx.getChildCount() > 2) {
        	if (ctx.getChild(2).getChildCount() != 1) {
        		int index = 0;
                for (int i = 0; i < ctx.children.size(); ++i) {
                    if (ctx.getChild(i).getChildCount() > 0) {
                        index = i;
                        break;
                    }
                }
                
                for (int i = 0; i < index; ++i) {
                    tokens.add(ctx.getChild(i).getText());
                }
        	} else if (ctx.getChild(0).getText().contentEquals("if")) {
        		int index = 0;
                for (int i = 0; i < ctx.children.size(); ++i) {
                    if (ctx.getChild(i).getChildCount() > 0) {
                        index = i;
                        break;
                    }
                }
                
                for (int i = 0; i < index; ++i) {
                    tokens.add(ctx.getChild(i).getText());
                }
        	} else if (ctx.getChild(0).getText().contentEquals("for")) {
        		tokens.add("foreach(");
        	}
        }
	}
	
	@Override
	public void exitStatement(JavaParser.StatementContext ctx) {
		if (ctx.getChild(0).getText().equals("return")) {
			if (ctx.getChild(ctx.getChildCount() - 1).getChildCount() == 0) {
				tokens.add(ctx.getChild(ctx.getChildCount() - 1).getText());
			}
		}
	}
	
	/*
	@Override
	public void enterExpression(JavaParser.ExpressionContext ctx) {
	}*/

	@Override
	public void exitExpression(JavaParser.ExpressionContext ctx) {
		if (ctx.equals(ctx.getParent().children.get(0))) {
			for (int i = 0; i < ctx.getParent().getChildCount(); ++i) {
				if (ctx.getParent().getChild(i).getChildCount() == 0) {
					tokens.add(ctx.getParent().getChild(i).getText());
				}
			}
		}
		if (ctx.getParent().getRuleIndex() == 77) {
			tokens.add(";");
		}
	}
	
	@Override
	public void enterPrimary(JavaParser.PrimaryContext ctx) {
		if (ctx.getChild(0).getChildCount() == 0 || ctx.getChild(0).getChildCount() == 1) {
			tokens.add(ctx.getChild(0).getText());
		}
	}

	@Override
	public void exitPrimary(JavaParser.PrimaryContext ctx) {
		if (ctx.getChildCount() > 1 && ctx.getChild(ctx.getChildCount() - 1).getChildCount() == 0) {
			tokens.add(ctx.getChild(ctx.getChildCount() - 1).getText());
		}
		if (ctx.getParent().getParent().getParent().getRuleIndex() == 38) {
			tokens.add(",");
		}
	}

	@Override
	public void enterMethodCall(JavaParser.MethodCallContext ctx) {
		tokens.add(ctx.getChild(0).getText());
		tokens.add(ctx.getChild(1).getText());
	}

	@Override
	public void exitMethodCall(JavaParser.MethodCallContext ctx) {
		tokens.add(ctx.getChild(ctx.getChildCount() - 1).getText());
	}
	
	/*@Override
	public void enterForControl(JavaParser.ForControlContext ctx) {
	}*/
	
	@Override
	public void exitForControl(JavaParser.ForControlContext ctx) {
		tokens.add(")");
	}
	
	@Override
	public void enterForInit(JavaParser.ForInitContext ctx) {
		
	}

	@Override
	public void exitForInit(JavaParser.ForInitContext ctx) {
		if (ctx.getParent().getRuleIndex() == 77) {
			tokens.add(";");
		}
	}

	@Override
	public void enterParExpression(JavaParser.ParExpressionContext ctx) {
		if (ctx.getChild(0).getChildCount() == 0) {
			tokens.add(ctx.getChild(0).getText());
		}
	}

	@Override
	public void exitParExpression(JavaParser.ParExpressionContext ctx) {
		if (ctx.getChild(2).getChildCount() == 0) {
			tokens.add(ctx.getChild(2).getText());
		}
	}
	
	@Override 
	public void enterArrayInitializer(JavaParser.ArrayInitializerContext ctx) { 
		if (ctx.getChild(0).getChildCount() == 0) {
			tokens.add(ctx.getChild(0).getText());
		}
	}
	
	@Override 
	public void exitArrayInitializer(JavaParser.ArrayInitializerContext ctx) { 
		if (ctx.getChild(ctx.getChildCount() - 1).getChildCount() == 0) {
			tokens.add(ctx.getChild(ctx.getChildCount() - 1).getText());
		}
	}
}