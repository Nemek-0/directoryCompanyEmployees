package com.fxmlControllers;

import com.entity.Employee;
import com.servece.EmployeeService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class AddEmployeeController implements Initializable {
    @FXML private TextField lastNameTextField;
    @FXML private TextField firstNameTextField;
    @FXML private TextField patronymicTextField;
    @FXML private DatePicker dateBirthDatePicker;
    @FXML private TextField addressResidenceTextField;
    @FXML private TextField positionTextField;
    @FXML private TextArea commentTextArea;
    @FXML private Label messageErrorLabel;
    private Stage stage;



    @FXML private void handleButton(){
        if(checkData()) {
            Employee employee = new Employee(
                    this.lastNameTextField.getText(),
                    this.firstNameTextField.getText(),
                    this.patronymicTextField.getText(),
                    this.dateBirthDatePicker.getValue(),
                    this.addressResidenceTextField.getText(),
                    this.positionTextField.getText(),
                    this.commentTextArea.getText()
            );
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

    }

    void setStage(Stage stage){
        this.stage = stage;
    }


}
