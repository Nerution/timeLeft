package com.litit.timeleft;

import com.litit.timeleft.utils.FileIO;
import com.litit.timeleft.utils.TemplateCreator;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class SettingsController implements AbstractController {
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
        LocalDate pickedDate = datePicker.valueProperty().getValue();

        if (pickedDate == null || now.isAfter(pickedDate)) {
            errorLabel.setText(ERROR_MESSAGE);
            errorLabel.setVisible(true);
            return;
        }

        startTicking(event, pickedDate);
        FileIO.write(pickedDate.toString());
    }

    public static void startTicking(Event event, LocalDate pickedDate) {
        Pair<HelloController, Stage> controllerStagePair = changeView(event);
        HelloController helloController = controllerStagePair.getKey();
        Stage stage = controllerStagePair.getValue();
        Timeline timeline = new Timeline(
                new KeyFrame(
                        javafx.util.Duration.millis(1000),
                        timeEvent -> {
                            Period timeLeftPeriod = Period.between(LocalDate.now(), pickedDate.minusDays(1));
                            Duration timeLeftDuration = Duration.between(
                                    LocalDateTime.now(),
                                    pickedDate.atStartOfDay().minusDays(1)
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
                            stage.sizeToScene();
                        }
                ));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private static Pair<HelloController, Stage> changeView(Event event) {
        if (event != null) {
            ((Node) event.getSource()).getScene().getWindow().hide();
        }
        TemplateCreator.create(HelloController.class, "hello-view.fxml");
        HelloController helloController = TemplateCreator.loader.getController();
        helloController.setWelcomeText(CALCULATING);
        Stage stage = TemplateCreator.stage;
        stage.sizeToScene();
        stage.show();
        return new Pair<>(helloController, stage);
    }
}
