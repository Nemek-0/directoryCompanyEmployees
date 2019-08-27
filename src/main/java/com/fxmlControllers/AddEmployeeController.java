package com.fxmlControllers;

import com.entity.Employee;
import com.entity.Position;
import com.servece.EmployeeService;
import com.servece.PositionService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class AddEmployeeController implements Initializable {
    @FXML private TextField lastNameTextField;
    @FXML private TextField firstNameTextField;
    @FXML private TextField patronymicTextField;
    @FXML private DatePicker dateBirthDatePicker;
    @FXML private TextField addressResidenceTextField;
    @FXML private ChoiceBox<Position> positionChoiceBox;
    @FXML private TextArea commentTextArea;
    @FXML private Label messageErrorLabel;
    private Stage stage;



    @FXML private void handleButton(){
        if(checkData()) {
            Employee employee = new Employee(
                    this.firstNameTextField.getText(),
                    this.lastNameTextField.getText(),
                    this.patronymicTextField.getText(),
                    this.dateBirthDatePicker.getValue(),
                    this.addressResidenceTextField.getText(),
                    this.positionChoiceBox.getValue(),
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
        PositionService positionService = new PositionService();
        ObservableList<Position> positions = FXCollections.observableArrayList(positionService.getAllPosition());
        this.positionChoiceBox.setItems(positions);
    }

    void setStage(Stage stage){
        this.stage = stage;
    }


}
