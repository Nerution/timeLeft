package com.litit.timeleft;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class SettingsController {
    public static final String ERROR_MESSAGE = "Wrong date! Please choose one more";
    public static final String CALCULATING = "CALCULATING!";
    private static double xOffset = 0;
    private static double yOffset = 0;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Label errorLabel;

    @FXML
    private void passDate(Event event) {
        LocalDate now = LocalDate.now().atStartOfDay().toLocalDate();
        LocalDate pickedDate = datePicker.valueProperty().getValue().minusDays(1);
        HelloController helloController = changeView(event);

        if (now.isAfter(pickedDate)) {
            errorLabel.setText(ERROR_MESSAGE);
            errorLabel.setVisible(true);
            return;
        }
        helloController.setWelcomeText(CALCULATING);
        Timeline timeline = new Timeline(
                new KeyFrame(
                        javafx.util.Duration.millis(1000),
                        timeEvent -> {
                            Period timeLeftPeriod = Period.between(LocalDate.now(), pickedDate);
                            Duration timeLeftDuration = Duration.between(
                                    LocalDateTime.now(),
                                    pickedDate.atStartOfDay()
                            );
                            helloController.setWelcomeText(
                                    String.format("%s years, %s months, %s days, %s hours, %s minutes, %s seconds",
                                            timeLeftPeriod.getYears(),
                                            timeLeftPeriod.getMonths(),
                                            timeLeftPeriod.getDays(),
                                            timeLeftDuration.toHoursPart(),
                                            timeLeftDuration.toMinutesPart(),
                                            timeLeftDuration.toSecondsPart())
                            );
                        }
                ));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private HelloController changeView(Event event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Scene scene;
        HelloController helloController;
        try {
            scene = new Scene(fxmlLoader.load(), 800, 100);
            helloController = fxmlLoader.getController();
            ((Node) event.getSource()).getScene().getWindow().hide();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            scene.setOnMousePressed(mouseEvent -> {
                xOffset = stage.getX() - mouseEvent.getScreenX();
                yOffset = stage.getY() - mouseEvent.getScreenY();
            });
            scene.setOnMouseDragged(mouseEvent -> {
                stage.setX(mouseEvent.getScreenX() + xOffset);
                stage.setY(mouseEvent.getScreenY() + yOffset);
            });
            stage.setScene(scene);
            stage.setAlwaysOnTop(true);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return helloController;
    }

}
