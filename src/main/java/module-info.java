module com.application.saejeu.saejeu1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    requires org.controlsfx.controls;


    opens com.application.saejeu.saejeu1 to javafx.fxml;
    exports com.application.saejeu.saejeu1;
    exports com.application.saejeu.saejeu1.controleur;
    opens com.application.saejeu.saejeu1.controleur to javafx.fxml;

}
