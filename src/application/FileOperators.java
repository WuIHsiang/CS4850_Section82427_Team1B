package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
        
        antlr.JavaTranslator translator = new antlr.JavaTranslator();
        
        antlr.JavaListener listener = new antlr.JavaListener();
        
        ParseTree tree = parser.compilationUnit();
        
        ParseTreeWalker.DEFAULT.walk(listener, tree);
        
        int indent = 0;
        translation +=("using System;\n");
        
        for (int i = 0; i < listener.tokens.size() - 1; i++) {
        	
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
        		translation +=("\n");
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
        	else if (listener.tokens.get(i).contentEquals("{")) {
        		translation += ("\n");
        		indent++;
        		for (int y = 0; y < indent; y++) {
        			translation += ("    ");
        		}
        	}
        	else if (listener.tokens.get(i).contentEquals("}") && !listener.tokens.get(i+1).contentEquals(";")) {
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
        translation = translation.replace("main", "Main");
        translation = translation.replace(", }", "}");
        
        tc.setText(translation);
        
        translation = "";
        
		return success;
	}
}