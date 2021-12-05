package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;

import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class FileOperators {
	
	static public boolean save(TextArea ta, char fileType) {
		boolean success = false;
		if (!ta.getText().trim().equals("")) {
    		Stage stage = null;
    		FileChooser fc = new FileChooser();
    		switch (fileType) {
    			case 'c':
    				fc.setTitle("Save C# File");
    	    		fc.getExtensionFilters().addAll(new ExtensionFilter("C# File", "*.cs"));
    	    		break;
    	    	case 'j':
        			fc.setTitle("Save Java File");
        	    	fc.getExtensionFilters().addAll(new ExtensionFilter("Java File", "*.java"));
        	    	break;
    		}
    		
    		File file = fc.showSaveDialog(stage);
    		if (file != null) {
    			try {
    				FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
    				fw.write(ta.getText());
    				fw.close();
    				success = true;
    			} catch (IOException e) {
    				success = false;
    			}
    		}
    	}
		return success;
	}
	
	static public boolean open(TextArea ta) {
		boolean success = false;
		ta.clear();
		Stage stage = null;
		FileChooser fc = new FileChooser();
		fc.setTitle("Open Java File");
		fc.getExtensionFilters().addAll(new ExtensionFilter("Java File", "*.java"));
		File file = fc.showOpenDialog(stage);
		try {
			Scanner in = new Scanner(file);
			while (in.hasNextLine()) {
				ta.appendText(in.nextLine());
				ta.appendText("\n");
				success = true;
			}
			in.close();
		} catch (FileNotFoundException e){
			System.err.println("invalid file");
			success = false;
		}
		return success;
	}
	
	@SuppressWarnings("deprecation")
	static public boolean translate(TextArea ta, TextArea tc) {
		boolean success = false;
		
		String content = ta.getText();
		
		String translation = "";
		
		StringBuilder sb = new StringBuilder();
        
        ANTLRInputStream input = new ANTLRInputStream(content);
        
        antlr.JavaLexer lexer = new antlr.JavaLexer(input);
        
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        
        antlr.JavaParser parser = new antlr.JavaParser(tokens);
        
        //antlr.JavaTranslator translator = new antlr.JavaTranslator();
        
        antlr.JavaListener listener = new antlr.JavaListener();
        
        ParseTree tree = parser.compilationUnit();
        
        System.out.println(tree.toStringTree(parser));
        ParseTreeWalker.DEFAULT.walk(listener, tree);
        int indent = 0, forDepth = 0;
        boolean forLoop = false;
        boolean arrayInit = false;
        for(int i=0;i<listener.tokens.size();i++) {
        	if((listener.tokens.get(i).contentEquals("=")
        			||listener.tokens.get(i).contentEquals("+")
        			||listener.tokens.get(i).contentEquals("-")
        			||listener.tokens.get(i).contentEquals("*")
        			||listener.tokens.get(i).contentEquals("/")
        			||listener.tokens.get(i).contentEquals(";")
        			||listener.tokens.get(i).contentEquals(")"))
        			&&listener.tokens.get(i-2).contentEquals("]")
        			&&!listener.tokens.get(i-4).contentEquals("int")
        			&&!listener.tokens.get(i-4).contentEquals("String")
        			&&!listener.tokens.get(i-4).contentEquals("double")
        			&&!listener.tokens.get(i-4).contentEquals("float")
        			&&!listener.tokens.get(i-4).contentEquals("boolean")
        			&&!listener.tokens.get(i-4).contentEquals("short")
        			&&!listener.tokens.get(i-4).contentEquals("long")
        			&&!listener.tokens.get(i-4).contentEquals("char")) {
        		Collections.swap(listener.tokens,i-2 , i-1);
        	}if(listener.tokens.get(i).contentEquals(",")&&
        			listener.tokens.get(i+1).contentEquals("}")) {
        		listener.tokens.remove(i);
        	}if(listener.tokens.get(i).contentEquals("]")
        			&&listener.tokens.get(i+3).contentEquals("length")) {
        		Collections.swap(listener.tokens,i,i+1);
        	}if(listener.tokens.get(i).contentEquals("]")
        			&&listener.tokens.get(i+2).contentEquals("[")) {
        		Collections.swap(listener.tokens,i,i+1);
        	}if(listener.tokens.get(i).contentEquals("=")
        			&&listener.tokens.get(i-1).contentEquals("]")
        			&&listener.tokens.get(i-3).contentEquals("[")
        			&&(listener.tokens.get(i-6).contentEquals("int")
        			||listener.tokens.get(i-6).contentEquals("String")
        			||listener.tokens.get(i-6).contentEquals("double")
        			||listener.tokens.get(i-6).contentEquals("float")
        			||listener.tokens.get(i-6).contentEquals("boolean")
        			||listener.tokens.get(i-6).contentEquals("short")
        			||listener.tokens.get(i-6).contentEquals("long")
        			||listener.tokens.get(i-6).contentEquals("char"))) {
        		Collections.swap(listener.tokens,i-2,i-1);
        	}if(listener.tokens.get(i).contentEquals(")")
        			&&listener.tokens.get(i+1).contentEquals("{")
        			&&listener.tokens.get(i-1).contentEquals("]")
        			&&(listener.tokens.get(i-6).contentEquals("int")
        			||listener.tokens.get(i-6).contentEquals("String")
        			||listener.tokens.get(i-6).contentEquals("double")
        			||listener.tokens.get(i-6).contentEquals("float")
        			||listener.tokens.get(i-6).contentEquals("boolean")
        			||listener.tokens.get(i-6).contentEquals("short")
        			||listener.tokens.get(i-6).contentEquals("long")
        			||listener.tokens.get(i-6).contentEquals("char"))) {
        		Collections.swap(listener.tokens,i-2,i-1);
        	}if(listener.tokens.get(i).contentEquals(")")
        			&&listener.tokens.get(i+1).contentEquals("{")
        			&&listener.tokens.get(i-1).contentEquals("]")
        			&&(listener.tokens.get(i-8).contentEquals("int")
        			||listener.tokens.get(i-8).contentEquals("String")
        			||listener.tokens.get(i-8).contentEquals("double")
        			||listener.tokens.get(i-8).contentEquals("float")
        			||listener.tokens.get(i-8).contentEquals("boolean")
        			||listener.tokens.get(i-8).contentEquals("short")
        			||listener.tokens.get(i-8).contentEquals("long")
        			||listener.tokens.get(i-8).contentEquals("char"))) {
        		Collections.swap(listener.tokens,i-2,i-1);
        	}
        	if(listener.tokens.get(i).contentEquals("=")
        			&&listener.tokens.get(i-1).contentEquals("]")
        			&&listener.tokens.get(i-3).contentEquals("[")
        			&&(listener.tokens.get(i-8).contentEquals("int")
        			||listener.tokens.get(i-8).contentEquals("String")
        			||listener.tokens.get(i-8).contentEquals("double")
        			||listener.tokens.get(i-8).contentEquals("float")
        			||listener.tokens.get(i-8).contentEquals("boolean")
        			||listener.tokens.get(i-8).contentEquals("short")
        			||listener.tokens.get(i-8).contentEquals("long")
        			||listener.tokens.get(i-8).contentEquals("char"))) {
        		Collections.swap(listener.tokens,i-1,i-2);
        	}if(listener.tokens.get(i).contentEquals("length")
        			&&listener.tokens.get(i-1).contentEquals(".")
        			&&listener.tokens.get(i-2).contentEquals("]")
        			&&listener.tokens.get(i-20).contentEquals("GetLength(1)")) {
        		listener.tokens.set(i, "GetLength(2)");
        		listener.tokens.remove(i-2);
        		listener.tokens.remove(i-3);
        		listener.tokens.remove(i-4);
        	}if(listener.tokens.get(i).contentEquals("length")
        			&&listener.tokens.get(i-1).contentEquals(".")
        			&&listener.tokens.get(i-2).contentEquals("]")
        			&&listener.tokens.get(i-20).contentEquals("GetLength(0)")) {
        		listener.tokens.set(i, "GetLength(1)");
        		listener.tokens.remove(i-2);
        		listener.tokens.remove(i-3);
        		listener.tokens.remove(i-4);
        	}if(listener.tokens.get(i).contentEquals("length")
        			&&listener.tokens.get(i+4).contentEquals(")")
        			&&listener.tokens.get(i+20).contentEquals("length")) {
        		listener.tokens.set(i,"GetLength(0)");
        	}
        	
        		
        }
        translation +=("using System;\n");
        
        for (int i = 0; i < listener.tokens.size() - 1; i++) {
        	if (listener.tokens.get(i).contentEquals("for")) {
        		forLoop = true;
        	}
        	if (listener.tokens.get(i).contentEquals("(") && forLoop) {
        		forDepth++;
        	}
        	else if (listener.tokens.get(i).contentEquals(")") && forLoop) {
        		forDepth--;
        		if (forDepth == 0) {
        			forLoop = false;
        		}
        	}
        	
        	if (listener.tokens.get(i).contentEquals("=") && listener.tokens.get(i + 1).contentEquals("{")) {
        		arrayInit = true;
        		
        	}
        	if (listener.tokens.get(i).contentEquals("}") && arrayInit) {
        		arrayInit = false;
        	}
        	
        	translation +=(listener.tokens.get(i));
        	if (!listener.tokens.get(i).contentEquals(";") 
        			&& !listener.tokens.get(i).contentEquals(".") 
        			&& !listener.tokens.get(i + 1).contentEquals(".")
        			&& !listener.tokens.get(i).contentEquals("(")
        			&& !listener.tokens.get(i + 1).contentEquals("(")
        			&& !listener.tokens.get(i + 1).contentEquals(")")
        			&& !listener.tokens.get(i + 1).contentEquals(";")) {
        		translation +=(" ");
        	}
        	
        	
        	
        	if (listener.tokens.get(i).contentEquals(";")) {
        		if (!forLoop) {
        			translation +=("\n");
        		}
        		if (!listener.tokens.get(i + 1).contentEquals("}") && !forLoop) {
        			for (int y = 0; y < indent; y++) {
            			translation += ("    ");
            		}
        		}
        		else if (!forLoop) {
        			for (int y = 0; y < indent - 1; y++) {
            			translation += ("    ");
            		}
        		}
        		else if (!listener.tokens.get(i + 1).contentEquals("}") && forLoop) {
        			for (int y = 0; y < indent; y++) {
            			translation += (" ");
            		}
        		}
        		else if (forLoop) {
        			for (int y = 0; y < indent - 1; y++) {
            			translation += (" ");
            		}
        		}
        	}
        	else if (listener.tokens.get(i).contentEquals("{") && !arrayInit) {
        		translation += ("\n");
        		indent++;
        		for (int y = 0; y < indent; y++) {
        			translation += ("    ");
        		}
        	}
        	else if (listener.tokens.get(i).contentEquals("}") && !listener.tokens.get(i + 1).contentEquals(";")) {
        		translation += ("\n");
        		indent--;
        		if (!listener.tokens.get(i + 1).contentEquals("}")) {
        			for (int y = 0; y < indent; y++) {
            			translation += ("    ");
            		}
        		}
        		else {
        			for (int y = 0; y < indent - 1; y++) {
            			translation += ("    ");
            		}
        		}
        	}
        }
        
        translation += (listener.tokens.get(listener.tokens.size() - 1));
        translation = translation.replace("System.out.println", "Console.WriteLine");
        translation = translation.replace("System.out.print", "Console.WriteLine");
        translation = translation.replace("main", "Main");
        translation = translation.replace(".length()", ".Length");
        translation = translation.replace(".length", ".Length");
        translation = translation.replace(".charAt(i)", "[i]");
        translation = translation.replaceAll("Scanner.*.*;","");
        translation = translation.replaceAll("=.*nextInt().*;","= Convert.ToInt32(Console.ReadLine());");
        translation = translation.replaceAll("=.*nextLine().*;","= Console.ReadLine();");
        translation = translation.replaceAll("=.*nextDouble().*;","= Convert.ToDouble(Console.ReadLine());");
        translation = translation.replaceAll("=.*nextFloat().*;","= float.Parse(Console.ReadLine());");
        translation = translation.replaceAll("=.*nextChar().*;","= Console.ReadLine()[0];");
        translation = translation.replaceAll("=.*nextShort().*;","= (short)Convert.ToInt32(Console.ReadLine());");
        translation = translation.replaceAll("=.*nextLong().*;","= (long)Convert.ToInt32(Console.ReadLine());");
        translation = translation.replaceAll("boolean","bool");
        translation = translation.replaceAll("=.*nextBoolean().*;","= bool.Parse(Console.ReadLine());");
        translation = translation.replaceAll("=.*nextByte().*;","= byte.Parse(Console.ReadLine());");
        translation = translation.replaceAll("\\] \\[",",");
        translation = translation.replaceAll("\\]\\[",",");
        
        tc.setText(translation);
        System.out.println(listener.tokens);
        translation = "";
        
		return success;
	}
}
