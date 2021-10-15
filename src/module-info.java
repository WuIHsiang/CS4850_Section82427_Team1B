module snrproj {
	requires javafx.controls;
	requires javafx.fxml;
	requires antlr;
	
	opens application to javafx.graphics, javafx.fxml;
}
