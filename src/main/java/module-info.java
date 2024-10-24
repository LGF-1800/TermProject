module com.vanier.TermProject {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.vanier.TermProject to javafx.fxml;
    exports com.vanier.TermProject;
}
