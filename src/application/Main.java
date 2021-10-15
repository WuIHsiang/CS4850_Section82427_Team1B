package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setOnCloseRequest(evt -> {
			if (!Controller.javaSaved || !Controller.cSaved) {
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "You have unsaved changes, are you sure you want to quit?", ButtonType.YES, ButtonType.CANCEL);
				ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);
				if (ButtonType.CANCEL.equals(result)) {
					evt.consume();
				}
			}
		});
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("GUI.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setMinHeight(400);
			primaryStage.setMinWidth(700);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}