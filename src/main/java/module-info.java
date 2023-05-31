module com.application.saejeu.saejeu1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.application.saejeu.saejeu1 to javafx.fxml;
    exports com.application.saejeu.saejeu1;
    exports controleur;
    opens controleur to javafx.fxml;
}