package com.fxmlControllers;

import com.entity.Employee;
import com.entity.Position;
import com.servece.EmployeeService;
import com.servece.PositionService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PositionController implements Initializable {


    @FXML private TableView<Position> positionTableView;
    @FXML private TableColumn<Position, Integer> idColumn;
    @FXML private TableColumn<Position, String> nameColumn;
    @FXML private TextField positionTextField;
    private ObservableList<Position> positionsData = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle rb) {
        this.idColumn.setCellValueFactory(new PropertyValueFactory<Position, Integer>("id"));
        this.nameColumn.setCellValueFactory(new PropertyValueFactory<Position, String>("name"));
        updateData();
    }

    private void updateData(){

        initData();
        positionTableView.setItems(this.positionsData);
    }

    private void initData() {
        PositionService positionService = new PositionService();
        this.positionsData.setAll(positionService.getAllPosition());
    }

    @FXML private void addPosition(){
        if(this.positionTextField.getText().isEmpty())
            return;
        PositionService positionService = new PositionService();
        positionService.savePosition(new Position(this.positionTextField.getText()));
        this.positionTextField.setText("");
        updateData();
    }

    @FXML private void deletePosition(){
        if(this.positionTableView.getSelectionModel().getSelectedItem() == null)
            return;
        PositionService positionService = new PositionService();
        positionService.deletePosition(this.positionTableView.getSelectionModel().getSelectedItem());
        updateData();
    }

    //------------------------------------------------------------------
    @FXML private void editPosition() throws IOException {

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/editPosition.fxml"));
        Parent root = loader.load();
        EditPositionController controller = loader.getController();
        if(this.positionTableView.getSelectionModel().getSelectedItem() == null)
            return;
        controller.setPosition(this.positionTableView.getSelectionModel().getSelectedItem());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Редоктирование");
        stage.initModality(Modality.APPLICATION_MODAL);
        controller.setStage(stage);
        controller.updateTextFiled();
        stage.showAndWait();
        updateData();
    }

}
