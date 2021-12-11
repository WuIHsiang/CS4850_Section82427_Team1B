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
		tokens.add(ctx.getText().replace("<String>", ""));
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
		System.out.println("Member: "+ctx.getChild(0).getText());
		System.out.println("Rule number: "+ctx.getChild(0).getChild(0).getText().getRuleIndex());
		//if(ctx.getChild(0).getRuleIndex()==26) {
		//	tokens.add(";");
		//}
		
		
	}
	/*
	@Override
	public void exitMemberDeclaration(JavaParser.MemberDeclarationContext ctx) {
	}*/
	@Override
	public void enterFieldDeclaration(JavaParser.FieldDeclarationContext ctx) { 
		//System.out.println("Member: "+ctx.getChild(0).getText());
		//System.out.println("Rule number: "+ctx.getRuleIndex());
		
		
		
	}
	@Override 
	public void enterMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
		if (ctx.getChild(0).getChildCount() == 0) {
			tokens.add(ctx.getChild(0).getText());
			if (ctx.getChild(1).getChildCount() == 0) {
				tokens.add(ctx.getChild(1).getText());
				//System.out.println("Members: "+ctx.getChild(1).getText());
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
		//System.out.println("Parameters: "+ctx.getChild(0).getText());
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
		
		var parent = ctx.getParent();
		if (parent.getChildCount() > 1 && parent.getChild(parent.getChildCount() - 1) != ctx)  {
			tokens.add(",");
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
				//System.out.println(p.getText());
			}
		}
		var parent = ctx.getParent();
		if (parent.getRuleIndex() == 79)
			tokens.add(parent.getChild(1).getText() + " in");
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
		if(ctx.getChild(1)!=null) {
			tokens.add(ctx.getChild(1).getText());
		}
		if(ctx.getChild(1)!=null) {
			if(ctx.getChild(2).getText().contains("new")) {
				tokens.add(ctx.getChild(2).getText().replaceAll("new.*", "new "));
				//System.out.println("Child1: "+ctx.getChild(2).getText());
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
		//System.out.println("Variable Declarators rule index: "+ctx.getParent().getRuleIndex());
		if(ctx.getParent().getRuleIndex()==26) {
			tokens.add(";");
		}
	}
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
		//System.out.println("Variable Type: "+ctx.getText());
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
	
	/*
	@Override
	public void enterExpression(JavaParser.ExpressionContext ctx) {
	}*/

	@Override
	public void exitExpression(JavaParser.ExpressionContext ctx) {
		if (ctx.equals(ctx.getParent().children.get(0))) {
			for (int i = 0; i < ctx.getParent().getChildCount(); ++i) {
				if (ctx.getParent().getChild(i).getChildCount() == 0&&ctx.getParent().getParent().getRuleIndex()!=89&&
						ctx.getParent().getParent().getParent().getRuleIndex()!=93) {
					tokens.add(ctx.getParent().getChild(i).getText());
					//System.out.println("Random 1st index: "+ctx.getParent().getChild(0).getText());
				}
			}
		}
		if (ctx.getParent().getRuleIndex() == 77) {
			tokens.add(";");
		}
		if(ctx.getChild(0).getText().contains("[")) {
			//System.out.println("Bracket: "+ctx.getChild(0).getText());
		}
	}
	
	@Override
	public void enterPrimary(JavaParser.PrimaryContext ctx) {
		if (ctx.getChild(0).getChildCount() == 0 || ctx.getChild(0).getChildCount() == 1
				&&ctx.getParent().getParent().getParent().getRuleIndex()!=89&&
				ctx.getParent().getParent().getParent().getParent().getRuleIndex()!=93) {
			tokens.add(ctx.getChild(0).getText());
			//System.out.println("Literal: "+ctx.getText());
		}
		
	}

	@Override
	public void exitPrimary(JavaParser.PrimaryContext ctx) {
		if (ctx.getChildCount() > 1 && ctx.getChild(ctx.getChildCount() - 1).getChildCount() == 0) {
			tokens.add(ctx.getChild(ctx.getChildCount() - 1).getText());
			//System.out.println("Literal: "+ctx.getChild(ctx.getChildCount() - 1).getText());
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
	public void enterArrayCreatorRest(JavaParser.ArrayCreatorRestContext ctx) { 
		if (ctx.getChild(0).getChildCount() == 0) {
			tokens.add(ctx.getText());
			/*tokens.add(ctx.getChild(0).getText());
			tokens.add(ctx.getChild(1).getText());
			tokens.add(ctx.getChild(2).getText());
			System.out.println("Bracket 1: "+ctx.getChild(0).getText());
			System.out.println("int 1: "+ctx.getChild(1).getText());
			System.out.println("Bracket 2: "+ctx.getChild(2).getText());
			tokens.add(ctx.getChild(3).getText());
			tokens.add(ctx.getChild(4).getText());
			tokens.add(ctx.getChild(5).getText());
			System.out.println("Bracket 3: "+ctx.getChild(3).getText());
			System.out.println("int 1: "+ctx.getChild(4).getText());
			System.out.println("Bracket 4: "+ctx.getChild(5).getText());*/
			
		}
	}
	@Override 
	public void enterCreatedName(JavaParser.CreatedNameContext ctx) { 
		if(ctx.getText().contains("ArrayList<")||ctx.getText().contains("LinkedList<")||ctx.getText().contains("HashMap<")||ctx.getText().contains("Set<")) {
			tokens.add(ctx.getText().replace("<.*", ""));
		}
		/*
		 public class Program{
	public static void Main(string[]args){
		ArrayList<String> thelist=new ArrayList<String>();
		thelist.add("FirstElement");
		thelist.add("SecondElement");
		System.out.println(thelist);
	}
}
		 */
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
		//System.out.println(ctx.getChild(ctx.getChildCount() - 1).getText());
	}
}