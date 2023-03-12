package com.litit.timeleft;

import com.litit.timeleft.utils.TemplateCreator;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HelloController implements AbstractController {

    @FXML
    private Label welcomeText;

    @FXML
    public void closeApp() {
        Platform.exit();
    }

    @FXML
    public void settings(Event event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
        TemplateCreator.create(SettingsController.class, "settings-view.fxml");
        Stage stage = TemplateCreator.stage;
        stage.sizeToScene();
        stage.show();
    }

    public void setWelcomeText(String text) {
        welcomeText.setText(text);
    }
}