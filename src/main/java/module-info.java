module com.application.saejeu.saejeu1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    requires org.controlsfx.controls;
    requires junit;
    requires org.testng;

    opens com.application.saejeu.saejeu1 to javafx.fxml;
    opens com.application.saejeu.saejeu1.controleur to javafx.fxml;
    opens com.application.saejeu.saejeu1.modele to junit;

    exports com.application.saejeu.saejeu1;
    exports com.application.saejeu.saejeu1.controleur;
}
