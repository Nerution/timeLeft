package com.litit.timeleft.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class TemplateCreator {
    private static double xOffset = 0;
    private static double yOffset = 0;

    public static FXMLLoader loader;
    public static Stage stage;

    public static void create(Class<?> controller, String resource) {
        FXMLLoader fxmlLoader = new FXMLLoader(controller.getResource(resource));
        Scene scene;
        Stage localStage;
        try {
            scene = new Scene(fxmlLoader.load());
            scene.setFill(Color.TRANSPARENT);
            localStage = new Stage();
            localStage.initStyle(StageStyle.TRANSPARENT);
            setMouseEvents(scene, localStage);
            localStage.setScene(scene);
            localStage.setAlwaysOnTop(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        loader = fxmlLoader;
        stage = localStage;
    }

    private static void setMouseEvents(Scene scene, Stage stage) {
        scene.setOnMousePressed(mouseEvent -> {
            xOffset = stage.getX() - mouseEvent.getScreenX();
            yOffset = stage.getY() - mouseEvent.getScreenY();
        });
        scene.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() + xOffset);
            stage.setY(mouseEvent.getScreenY() + yOffset);
        });
    }
}
