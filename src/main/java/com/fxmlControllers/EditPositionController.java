package com.fxmlControllers;

import com.entity.Employee;
import com.entity.Position;
import com.servece.PositionService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditPositionController implements Initializable {


    @FXML
    private TextField editPositionTextField;
    private Position position;
    private Stage stage;

    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void updateTextFiled(){
        this.editPositionTextField.setText(this.position.getName());
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    @FXML private void handleButton(){
        this.position.setName(this.editPositionTextField.getText());
        PositionService positionService = new PositionService();
        positionService.updatePosition(this.position);
        this.stage.close();
    }
}
