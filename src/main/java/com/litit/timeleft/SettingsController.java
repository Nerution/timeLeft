package com.litit.timeleft;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.io.IOException;
import java.time.LocalDate;

public class SettingsController {
    public static final String ERROR_MESSAGE = "Wrong date! Please choose one more";

    @FXML
    private DatePicker datePicker;

    @FXML
    private Label errorLabel;

    @FXML
    private void passDate(Event event) throws IOException {
        LocalDate now = LocalDate.now().atStartOfDay().toLocalDate();
        LocalDate pickedDate = datePicker.valueProperty().getValue();

        if (now.isAfter(pickedDate)) {
            errorLabel.setText(ERROR_MESSAGE);
            errorLabel.setVisible(true);
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            fxmlLoader.load();
            HelloController helloController = fxmlLoader.getController();
            helloController.setEndDate(pickedDate);
            ((Node) event.getSource()).getScene().getWindow().hide();
        }
    }

}
