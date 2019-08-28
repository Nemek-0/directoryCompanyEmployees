package com.fxmlControllers;

import com.entity.Employee;
import com.entity.PhoneNumber;
import com.entity.Position;
import com.servece.EmployeeService;
import com.servece.PhoneNumberService;
import com.servece.PositionService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EditEmployeeController implements Initializable {

    @FXML private ComboBox<String> typePhoneNumberComboBox;
    @FXML private TableView<PhoneNumber> phoneNumberTableView;
    @FXML private TableColumn<PhoneNumber, String> typePhoneNumberColumn;
    @FXML private TableColumn<PhoneNumber, String> phoneNumberColumn;
    @FXML private TextField phoneNumberTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField firstNameTextField;
    @FXML private TextField patronymicTextField;
    @FXML private DatePicker dateBirthDatePicker;
    @FXML private TextField addressResidenceTextField;
    @FXML private ChoiceBox<Position> positionChoiceBox;
    @FXML private TextArea commentTextArea;
    @FXML private Label messageErrorLabel;
    private Stage stage;
    private Employee employee = new Employee();

    @FXML private void handleButton(){
        if(checkData()) {
            this.employee.setFirstName(this.firstNameTextField.getText());
            this.employee.setLastName(this.lastNameTextField.getText());
            this.employee.setPatronymic(this.patronymicTextField.getText());
            this.employee.setAddressResidence(this.addressResidenceTextField.getText());
            this.employee.setDateBirth(this.dateBirthDatePicker.getValue());
            this.employee.setPosition(this.positionChoiceBox.getValue());
            this.employee.setComment(this.commentTextArea.getText());
            this.employee.setPhoneNumbers(this.phoneNumberTableView.getItems());
            editEmployee(employee);
            this.stage.close();
        }
        else {
            this.messageErrorLabel.setText("Ошибка! Внесены не верные данные");
        }
    }

    private boolean checkData() {
        if(this.lastNameTextField.getText().length() > 45)
            return false;
        if(this.firstNameTextField.getText().length() > 45)
            return false;
        if (this.patronymicTextField.getText().length() > 45)
            return false;
        if(this.dateBirthDatePicker.getValue() == null)
            return false;
        if(this.addressResidenceTextField.getText().length() > 255)
            return false;
        if(this.commentTextArea.getText().length() > 1000)
            return false;
        return true;
    }

    private void editEmployee(Employee employee){
        EmployeeService employeeService = new EmployeeService();
        employeeService.updateEmployee(employee);
    }

    public void initialize(URL url, ResourceBundle rb) {
        this.typePhoneNumberColumn.setCellValueFactory(new PropertyValueFactory<PhoneNumber, String>("typePhoneNumber"));
        this.phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<PhoneNumber, String>("phoneNumber"));
        PositionService positionService = new PositionService();
        ObservableList<Position> positions = FXCollections.observableArrayList(positionService.getAllPosition());
        this.positionChoiceBox.setItems(positions);
    }

    void setStage(Stage stage){
        this.stage = stage;
    }

    void setEmployee(Employee employee){
        this.employee = employee;
        updateData();
    }

    private void updateData(){
        updateTextField();
        updatePhoneNumbersTable();
    }

    private void updateTextField(){
        this.lastNameTextField.setText(this.employee.getLastName());
        this.firstNameTextField.setText(this.employee.getFirstName());
        this.patronymicTextField.setText(this.employee.getPatronymic());
        this.dateBirthDatePicker.setValue(this.employee.getDateBirth());
        this.addressResidenceTextField.setText(this.employee.getAddressResidence());
        this.positionChoiceBox.setValue(this.employee.getPosition());
        this.commentTextArea.setText(this.employee.getComment());
    }

    private void updatePhoneNumbersTable(){
        this.phoneNumberTableView.getItems().setAll(this.employee.getPhoneNumbers());
    }

    @FXML private void addPhoneNumber(){
        PhoneNumber phoneNumber = new PhoneNumber(
                this.employee, this.typePhoneNumberComboBox.getValue(), this.phoneNumberTextField.getText());
        this.phoneNumberTableView.getItems().add(phoneNumber);
    }
    @FXML private void deletePhoneNumber(){
        if(this.phoneNumberTableView.getSelectionModel().getSelectedItem() == null)
            return;
        this.phoneNumberTableView.getItems().remove(this.phoneNumberTableView.getSelectionModel().getSelectedItem());

    }
}
