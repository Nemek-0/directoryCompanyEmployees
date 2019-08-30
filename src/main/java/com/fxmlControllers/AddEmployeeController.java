package com.fxmlControllers;

import com.entity.Employee;
import com.entity.PhoneNumber;
import com.entity.Position;
import com.servece.EmployeeService;
import com.servece.PositionService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class AddEmployeeController implements Initializable {
    @FXML private TextField phoneNumberTextField;
    @FXML private ComboBox<String> typePhoneNumberComboBox;
    @FXML private TableView<PhoneNumber> phoneNumberTableView;
    @FXML private TableColumn<PhoneNumber, String> typePhoneNumberColumn;
    @FXML private TableColumn<PhoneNumber, String> phoneNumberColumn;
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
            employee.setFirstName(this.firstNameTextField.getText());
            employee.setLastName(this.lastNameTextField.getText());
            employee.setPatronymic(this.patronymicTextField.getText());
            employee.setAddressResidence(this.addressResidenceTextField.getText());
            employee.setDateBirth(this.dateBirthDatePicker.getValue());
            employee.setPosition(this.positionChoiceBox.getValue());
            employee.setComment(this.commentTextArea.getText());
            employee.setPhoneNumbers(this.phoneNumberTableView.getItems());
            addEmployee(employee);
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

    private void addEmployee(Employee employee){
        EmployeeService employeeService = new EmployeeService();
        employeeService.saveEmployee(employee);
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

    @FXML private void addPhoneNumber(){
        if(this.phoneNumberTextField.getText().equals("")){
            return;
        }
        PhoneNumber phoneNumber = new PhoneNumber(
                this.typePhoneNumberComboBox.getValue(), this.phoneNumberTextField.getText());
        this.employee.addPhoneNumber(phoneNumber);
        this.phoneNumberTableView.getItems().add(phoneNumber);
    }

    @FXML private void removePhoneNumber(){
        if(this.phoneNumberTableView.getSelectionModel().getSelectedItem() == null)
            return;
        this.employee.removePhoneNumbers(this.phoneNumberTableView.getSelectionModel().getSelectedItem());
    }

}
