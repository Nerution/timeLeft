package com.litit.timeleft;

import com.litit.timeleft.utils.TemplateCreator;
import javafx.application.Application;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        TemplateCreator.create(HelloController.class, "hello-view.fxml");
        stage = TemplateCreator.stage;
        stage.sizeToScene();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}