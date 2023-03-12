package com.litit.timeleft;

import com.litit.timeleft.utils.FileIO;
import com.litit.timeleft.utils.TemplateCreator;
import javafx.application.Application;
import javafx.stage.Stage;

import java.time.LocalDate;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        String dateString = FileIO.read();
        if (dateString != null) {
            getDateFromFile(dateString);
            return;
        }
        TemplateCreator.create(HelloController.class, "hello-view.fxml");
        stage = TemplateCreator.stage;
        stage.sizeToScene();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void getDateFromFile(String dateString) {
        LocalDate date = LocalDate.parse(dateString);
        SettingsController.startTicking(null, date);
    }
}
