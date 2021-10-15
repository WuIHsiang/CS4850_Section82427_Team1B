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
	static public boolean translate(TextArea ta) {
		boolean success = false;
		
		String content = ta.getText();
        System.out.println("Java File:\n" + content + "\n\n");
        
        ANTLRInputStream input = new ANTLRInputStream(content);
        
        antlr.JavaLexer lexer = new antlr.JavaLexer(input);
        
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        
        antlr.JavaParser parser = new antlr.JavaParser(tokens);
        
        ParseTree tree = parser.compilationUnit();
        
        System.out.println("ParseTree:\n" + tree.toStringTree(parser) + "\n");
        
		return success;
	}
}
