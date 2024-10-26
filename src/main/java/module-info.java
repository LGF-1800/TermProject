module com.vanier.TermProject {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;

    opens com.vanier.TermProject to javafx.fxml;
    exports com.vanier.TermProject;
}
