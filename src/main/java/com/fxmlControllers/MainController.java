package com.fxmlControllers;

import com.entity.Employee;
import com.entity.PhoneNumber;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class MainController implements Initializable {

    @FXML private TableView<Employee> employeesTable;
    @FXML private TableColumn<Employee, Integer> idColumn;
    @FXML private TableColumn<Employee, String> firstNameColumn;
    @FXML private TableColumn<Employee, String> lastNameColumn;
    @FXML private TableColumn<Employee, String> patronymicColumn;
    @FXML private TableColumn<Employee, String> positionColumn;
    @FXML private ChoiceBox<String> searchChoiceBox;
    @FXML private TextField searchTextField;

    private ObservableList<Employee> employeesData = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle rb) {
        initializeTableView();
        this.searchChoiceBox.setItems(FXCollections.observableArrayList("ФИО", "Номер телефона", "Должность"));
        this.searchChoiceBox.setValue("ФИО");
        updateData();
    }

    private void initializeTableView(){
        this.idColumn.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("id"));
        this.firstNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));
        this.lastNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
        this.patronymicColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("patronymic"));
        this.positionColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("position"));
    }

    private void updateData(){
        initData();
        employeesTable.setItems(employeesData);
    }

    private void initData() {
        EmployeeService employeeService = new EmployeeService();
        employeesData.setAll(employeeService.getAllEmployees());
    }

    @FXML private void handleSearchButton(){
        ObservableList<Employee> searchEmployeesData = FXCollections.observableArrayList();
        if(this.searchChoiceBox.getValue().equals("ФИО")) {
            findByName(searchEmployeesData);
        }
        if(this.searchChoiceBox.getValue().equals("Номер телефона")) {
            findByPhoneNumber(searchEmployeesData);
        }
        if(this.searchChoiceBox.getValue().equals("Должность"))
            findByPosition(searchEmployeesData);
        employeesTable.setItems(searchEmployeesData);
    }

    private void findByName(ObservableList<Employee> searchEmployeesData){
        for (Employee employee: this.employeesData){
            if(employee.isName(this.searchTextField.getText()))
                searchEmployeesData.add(employee);
        }

    }

    private void findByPhoneNumber(ObservableList<Employee> searchEmployeesData){
        for (Employee employee: this.employeesData){
            if(employee.isPhoneNumberOwnedEmployee(new PhoneNumber(this.searchTextField.getText())))
                searchEmployeesData.add(employee);
        }
    }

    private void findByPosition(ObservableList<Employee> searchEmployeesData){
        for (Employee employee: this.employeesData){
            if(employee.getPosition().isNamePosition(this.searchTextField.getText()))
                searchEmployeesData.add(employee);
        }
    }

    @FXML private void searchReset(){
        this.searchTextField.setText("");
        this.employeesTable.setItems(this.employeesData);
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
        stage.initModality(Modality.APPLICATION_MODAL);
        controller.setStage(stage);
        stage.showAndWait();
        updateData();
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
        controller.setStage(stage);
        stage.showAndWait();
        updateData();
    }

    @FXML private void deleteEmployee(){
        EmployeeService employeeService = new EmployeeService();
        employeeService.deleteEmployee(this.employeesTable.getSelectionModel().getSelectedItem());
        updateData();
    }

    @FXML private void editPosition() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/position.fxml"));
        Parent root = loader.load();
        PositionController controller = loader.getController();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Редоктирование");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        updateData();
    }
}
