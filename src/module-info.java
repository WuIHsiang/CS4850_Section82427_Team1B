module snrproj{
	requires antlr;
	requires javafx.graphics;
    requires javafx.controls;
    requires javafx.media;
    requires javafx.base;
    requires javafx.web;
    requires javafx.swing;
    requires javafx.fxml;
	
	opens application to javafx.graphics, javafx.fxml;
}
