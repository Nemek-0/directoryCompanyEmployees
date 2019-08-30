package com.fxmlControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class HelpController implements Initializable {

    private Stage stage;
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML private void close () {
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
