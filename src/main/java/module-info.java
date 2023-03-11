module com.litit.timeleft {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;

    opens com.litit.timeleft to javafx.fxml;
    exports com.litit.timeleft;
}