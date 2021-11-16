package application;

import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;

public class Controller {

    @FXML
    private MenuItem MenuOpenJavaFile;

    @FXML
    private MenuItem MenuCloseFile;

    @FXML
    private MenuItem MenuSaveJavaFile;

    @FXML
    private MenuItem MenuSaveCFile;

    @FXML
    private TextArea TextAreaJava;

    @FXML
    private Button ButtonClearAll;

    @FXML
    private Button ButtonSaveJava;

    @FXML
    private Label StatusLabelJava;

    @FXML
    private TextArea TextAreaC;
    
    @FXML
    private Button ButtonTranslate;

    @FXML
    private Button ButtonSaveC;

    @FXML
    private Label LastModifiedStatusLabel;
    
    @FXML
    private MenuItem MenuUndo;

    @FXML
    private MenuItem MenuRedo;

    @FXML
    private MenuItem MenuCut;

    @FXML
    private MenuItem MenuCopy;

    @FXML
    private MenuItem MenuPaste;

    @FXML
    private MenuItem MenuSelectAll;

    @FXML
    private MenuItem MenuUnselect;
    
    static boolean javaSaved = false;
    static boolean cSaved = false;
    Date cSaveDate;

    @FXML
    private void initialize() {
    	TextAreaC.textProperty().addListener((obs,old,niu)->{
    	    cUnsaveState();
    	});
    	TextAreaJava.textProperty().addListener((obs,old,niu)->{
    	    javaUnsaveState();
    	});
    }
    
    //primary button commands
    @FXML
    void ButtonClearAllClicked(ActionEvent event) {
    	TextAreaJava.clear();
    	TextAreaC.clear();
    	javaUnsaveState();
    	cUnsaveState();
    }

    @FXML
    void ButtonSaveCClicked(ActionEvent event) {
    	boolean success = FileOperators.save(TextAreaC, 'c');
    	if (success) {
    		cSaveState();
    	}
    }

    @FXML
    void ButtonSaveJavaClicked(ActionEvent event) {
    	boolean success = FileOperators.save(TextAreaJava, 'j');
    	if (success) {
    		javaSaveState();
    	}
    }

    @FXML
    void ButtonTranslateClicked(ActionEvent event) {
    	boolean success = FileOperators.translate(TextAreaJava, TextAreaC);
    	
    }

    //file menu commands
    @FXML
    void MenuCloseFileClicked(ActionEvent event) {
    	ButtonClearAllClicked(event);
    }

    @FXML
    void MenuOpenJavaFileClicked(ActionEvent event) {
    	boolean success = FileOperators.open(TextAreaJava);
    	if (success) {
    		javaSaveState();
    	}
    }

    @FXML
    void MenuSaveCFileClicked(ActionEvent event) {
    	boolean success = FileOperators.save(TextAreaC, 'c');
    	if (success) {
    		cSaveState();
    	}
    }

    @FXML
    void MenuSaveJavaFileClicked(ActionEvent event) {
    	boolean success = FileOperators.save(TextAreaJava, 'j');
    	if (success) {
    		javaSaveState();
    	}
    }
    
    //edit menu commands
    @FXML
    void MenuCopyClicked(ActionEvent event) {
    	TextAreaJava.copy();
    }

    @FXML
    void MenuCutClicked(ActionEvent event) {
    	TextAreaJava.cut();
    }
    
    @FXML
    void MenuPasteClicked(ActionEvent event) {
    	TextAreaJava.paste();
    }
    
    @FXML
    void MenuUndoClicked(ActionEvent event) {
    	TextAreaJava.undo();
    }

    @FXML
    void MenuRedoClicked(ActionEvent event) {
    	TextAreaJava.redo();
    }
    
    @FXML
    void MenuSelectAllClicked(ActionEvent event) {
    	TextAreaJava.selectAll();
    }

    @FXML
    void MenuUnselectClicked(ActionEvent event) {
    	TextAreaJava.deselect();
    }
    
    
    //save state methods
    void javaSaveState() {
    	javaSaved = true;
    	StatusLabelJava.setText("Saved");
    }
    
    void javaUnsaveState() {
    	javaSaved = false;
    	StatusLabelJava.setText("Unsaved");
    }
    
    void cSaveState() {
    	cSaved = true;
    	Date date = new Date();
    	SimpleDateFormat format = new SimpleDateFormat("hh:mm:ssa");
    	LastModifiedStatusLabel.setText(format.format(date));
    }
    
    void cUnsaveState() {
    	cSaved = false;
    	LastModifiedStatusLabel.setText("No Changes made");
    }
}