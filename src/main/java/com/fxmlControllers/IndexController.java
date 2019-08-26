package com.fxmlControllers;

import com.entity.Employee;
import com.servece.EmployeeService;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class IndexController implements Initializable {
    @FXML private TableView<Employee> employeesTable;
    @FXML private TableColumn<Employee, Integer> idColumn;
    @FXML private TableColumn<Employee, String> firstNameColumn;
    @FXML private TableColumn<Employee, String> lastNameColumn;
    @FXML private TableColumn<Employee, String> patronymicColumn;
    @FXML private TableColumn<Employee, String> positionColumn;

    private ObservableList<Employee> employeesData = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle rb) {

        this.idColumn.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("id"));
        this.firstNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));
        this.lastNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
        this.patronymicColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("patronymic"));
        this.positionColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("position"));
        updateDate();
    }
    private void updateDate(){
        initData();
        employeesTable.setItems(employeesData);
    }

    private void initData() {
        EmployeeService employeeService = new EmployeeService();
        //List<Employee> list = employeeService.getAllEmployees();
        employeesData.addAll(employeeService.getAllEmployees());

    }



    //Далее находятся переходы к другим формам----------------------------------------------------------------
    @FXML private void addEmployee() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addEmployee.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Добовление сотрудника");
        AddEmployeeController controller = loader.getController();
        controller.setStage(stage);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        updateDate();
    }

    @FXML private void editEmployee() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/editEmployee.fxml"));
        Parent root = loader.load();
        EditEmployeeController controller = loader.getController();
        if(this.employeesTable.getSelectionModel().getSelectedItem() == null)
            return;
        controller.setEmployee(this.employeesTable.getSelectionModel().getSelectedItem());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Редоктирование");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        updateDate();
    }

    @FXML private void deleteEmployee(){
        EmployeeService employeeService = new EmployeeService();
        employeeService.deleteEmployee(this.employeesTable.getSelectionModel().getSelectedItem());
        updateDate();
    }

}
