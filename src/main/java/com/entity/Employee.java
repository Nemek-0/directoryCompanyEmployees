package com.entity;

import com.servece.EmployeeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

    private String patronymic;

    private LocalDate dateBirth;

    private String addressResidence;

    private String position;

    private String comment;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhoneNumber> phoneNumbers;

    public Employee(){
    }

    public Employee(String lastName, String firstName, String patronymic){
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
    }

    public Employee(String firstName, String lastName, String patronymic, LocalDate dateBirth, String addressResidence, String position, String comment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.dateBirth = dateBirth;
        this.addressResidence = addressResidence;
        this.position = position;
        this.comment = comment;
        this.phoneNumbers = new ArrayList<PhoneNumber>();
    }

    public void addPhoneNumber(PhoneNumber phoneNumber) {
        phoneNumber.setEmployee(this);
        phoneNumbers.add(phoneNumber);
    }

    public void removePhoneNumbers(PhoneNumber phoneNumber) {
        this.phoneNumbers.remove(phoneNumber);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getAddressResidence() {
        return addressResidence;
    }

    public void setAddressResidence(String addressResidence) {
        this.addressResidence = addressResidence;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public boolean isPhoneNumber(PhoneNumber phoneNumber){
        System.out.println(this.phoneNumbers);
        for(PhoneNumber thisPhoneNumber : this.phoneNumbers){
            if(thisPhoneNumber.isPhoneNumber(phoneNumber))
                return true;
        }
        return false;
    }

    public boolean isName(String name) {
        String[] arrayName = name.split(" ");

        for(String firstName: arrayName){
            if(this.firstName.equals(firstName))
                return true;
        }
        for(String lastName: arrayName){
            if(this.lastName.equals(lastName))
                return true;
        }
        for(String patronymic: arrayName){
            if(this.patronymic.equals(patronymic))
                return true;
        }
        return false;
    }
}
