package com.litit.timeleft;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ResourceBundle;

public class HelloController {

    @FXML
    private Label welcomeText;

    @FXML
    public void closeApp() {
        Platform.exit();
    }

    @FXML
    public void settings(Event event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("settings-view.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 800, 100);
            Stage stage = new Stage();
//            stage.setOpacity(0.1);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setWelcomeText(String text){
        welcomeText.setText(text);
    }
}