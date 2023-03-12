package com.litit.timeleft;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

public class HelloController implements Initializable {

    @FXML
    private Label welcomeText;

    private void init(LocalDateTime endDate) {
        welcomeText.setText(
                String.format("%s years, %s months, %s days, %s hours, %s minutes, %s seconds", 0, 0, 0, 0, 0, 0)
        );
        Timeline timeline = new Timeline(
                new KeyFrame(
                        javafx.util.Duration.millis(1000),
                        event -> {
                            Period timeLeftPeriod = Period.between(LocalDate.now(), endDate.toLocalDate());
                            Duration timeLeftDuration = Duration.between(LocalDateTime.now(), endDate);
                            welcomeText.setText(
                                    String.format("%s years, %s months, %s days, %s hours, %s minutes, %s seconds",
                                            timeLeftPeriod.getYears(), timeLeftPeriod.getMonths(), timeLeftPeriod.getDays(),
                                            timeLeftDuration.toHoursPart(), timeLeftDuration.toMinutesPart(), timeLeftDuration.toSecondsPart())
                            );
                        }
                ));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @FXML
    public void closeApp() {
        Platform.exit();
    }

    @FXML
    public void settings() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("settings-view.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 400, 100);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setEndDate(LocalDate endDate) {
        init(endDate.atStartOfDay());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LocalDateTime endDate = LocalDateTime.of(1994, 1, 5, 0, 0).plusYears(35);
        init(endDate);
    }
}