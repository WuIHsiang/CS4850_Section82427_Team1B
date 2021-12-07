package antlr;

import java.util.ArrayList;

import org.antlr.v4.runtime.tree.ParseTree;

public class JavaListener extends JavaParserBaseListener {
	public ArrayList<String> tokens = new ArrayList<String>();
	
	
	
	@Override
	public void enterImportDeclaration(JavaParser.ImportDeclarationContext ctx) {
		tokens.add("import");
		tokens.add(ctx.getText().replace("import", " ")+"\n");
	}
	
	@Override
	public void enterClassOrInterfaceModifier(JavaParser.ClassOrInterfaceModifierContext ctx) {
		tokens.add(ctx.getText());
		
	}
	
	@Override
	public void exitClassOrInterfaceModifier(JavaParser.ClassOrInterfaceModifierContext ctx) {
	}
	
	@Override
	public void enterClassOrInterfaceType(JavaParser.ClassOrInterfaceTypeContext ctx) {
		if(ctx.getParent().getParent().getRuleIndex()!=40) {
			tokens.add(ctx.getText());
		}
	}

	@Override
	public void exitClassOrInterfaceType(JavaParser.ClassOrInterfaceTypeContext ctx) {
	}

	@Override
	public void enterClassDeclaration(JavaParser.ClassDeclarationContext ctx) { 
		tokens.add(ctx.CLASS().toString());
		tokens.add(ctx.IDENTIFIER().toString());
		if(ctx.getText().contains("extends")) {
			tokens.add(ctx.getText().replaceAll(".*extends.*", "extends"));
		}if(ctx.getText().contains("implements")) {
			tokens.add(ctx.getText().replaceAll(".*implements.*", "implements"));
		}
	}
	public void enterInterfaceDeclaration(JavaParser.InterfaceDeclarationContext ctx) { 
		tokens.add(ctx.getChild(0).toString());
		tokens.add(ctx.IDENTIFIER().toString());
	}
	public void enterConstructorDeclaration(JavaParser.ConstructorDeclarationContext ctx) { 
		tokens.add(ctx.getChild(0).getText().toString());
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
	
	@Override
	public void enterFieldDeclaration(JavaParser.FieldDeclarationContext ctx) { 
		
		
		
	}
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
		if(ctx.getChild(1).getChildCount() == 0) {
			tokens.add(ctx.getChild(1).getText());
		}
		else if (ctx.getChild(2).getChildCount() == 0) {
			tokens.add(ctx.getChild(2).getText());
		}
	}
	
	@Override
	public void exitFormalParameter(JavaParser.FormalParameterContext ctx) {
		if (ctx.getChild(ctx.getChildCount() - 1).getChild(0).getChildCount() == 0) {
			tokens.add(ctx.getChild(ctx.getChildCount() - 1).getChild(0).getText());
		}
		
		var parent = ctx.getParent();
		if (parent.getChildCount() > 1 && parent.getChild(parent.getChildCount() - 1) != ctx)  {
			tokens.add(",");
		}
	}
	

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
	

	@Override
	public void exitTypeType(JavaParser.TypeTypeContext ctx) {
		for (ParseTree p : ctx.children) {
			if (p.getChildCount() == 0) {
				tokens.add(p.getText());
			}
		}
		var parent = ctx.getParent();
		if (parent.getRuleIndex() == 79)
			tokens.add(parent.getChild(1).getText() + " in");
	}
	
	@Override
	public void enterVariableDeclaratorId(JavaParser.VariableDeclaratorIdContext ctx) {
		if(ctx.getParent().getParent().getRuleIndex()==34&&ctx.getParent().getParent().getChildCount()>1) {
			tokens.add(",");
		}
		
	}
	
	@Override
	public void enterVariableDeclarator(JavaParser.VariableDeclaratorContext ctx) {
		tokens.add(ctx.getChild(0).getText());
		if(ctx.getChild(1)!=null) {
			tokens.add(ctx.getChild(1).getText());
		}
		if(ctx.getChild(1)!=null) {
			if(ctx.getChild(2).getText().contains("new")) {
				tokens.add(ctx.getChild(2).getText().replaceAll("new.*", "new "));
				if(!ctx.getChild(2).getText().contains("int")&&
						!ctx.getChild(2).getText().contains("String")&&
						!ctx.getChild(2).getText().contains("double")&&
						!ctx.getChild(2).getText().contains("float")&&
						!ctx.getChild(2).getText().contains("char")&&
						!ctx.getChild(2).getText().contains("long")&&
						!ctx.getChild(2).getText().contains("short")&&
						!ctx.getChild(2).getText().contains("Node")) {
							tokens.add(ctx.getChild(2).getText().replace("new", ""));
				}
				
			}
		}
		
		
	}
	
	@Override
	public void exitVariableDeclarators(JavaParser.VariableDeclaratorsContext ctx) { 
		if(ctx.getParent().getRuleIndex()==26) {
			tokens.add(";");
		}
	}
	

	@Override
	public void enterPrimitiveType(JavaParser.PrimitiveTypeContext ctx) {
		tokens.add(ctx.getText());
	}
	
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
        	}else if (ctx.getChild(0).getText().contentEquals("while")) {
        		tokens.add("while");
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
	

	@Override
	public void exitExpression(JavaParser.ExpressionContext ctx) {
		if (ctx.equals(ctx.getParent().children.get(0))) {
			for (int i = 0; i < ctx.getParent().getChildCount(); ++i) {
				if (ctx.getParent().getChild(i).getChildCount() == 0&&ctx.getParent().getParent().getRuleIndex()!=89&&
						ctx.getParent().getParent().getParent().getRuleIndex()!=93) {
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
		if ((ctx.getChild(0).getChildCount() == 0 || ctx.getChild(0).getChildCount() == 1)
				&&ctx.getParent().getParent().getParent().getRuleIndex()!=89&&
				ctx.getParent().getParent().getParent().getParent().getRuleIndex()!=93&&
				ctx.getParent().getParent().getRuleIndex()!=92) {
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
	public void enterArrayCreatorRest(JavaParser.ArrayCreatorRestContext ctx) { 
		if (ctx.getChild(0).getChildCount() == 0) {
			tokens.add(ctx.getText());
			
		}
	}
	@Override 
	public void enterCreatedName(JavaParser.CreatedNameContext ctx) { 
		if(ctx.getText().contains("ArrayList<")||ctx.getText().contains("LinkedList<")||ctx.getText().contains("HashMap<")||ctx.getText().contains("Stack<")
				||ctx.getText().contains("List<")) {
			tokens.add(ctx.getText().replace("<.*>", ""));
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