package com.litit.timeleft;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;

    private void init() {
        welcomeText.setText("xzczxc");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }
}