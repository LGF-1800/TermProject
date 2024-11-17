module com.vanier.TermProject {
    requires javafx.controls;
    requires javafx.fxml;
	requires transitive javafx.graphics;
	requires javafx.base;

    opens com.vanier.TermProject to javafx.fxml;
    exports com.vanier.TermProject;
}
