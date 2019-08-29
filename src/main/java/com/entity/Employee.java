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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "positionId")
    private Position position;

    private String comment;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhoneNumber> phoneNumbers = new ArrayList<>();

    public Employee(){
    }

    public Employee(String lastName, String firstName, String patronymic){
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
    }

    public Employee(String firstName, String lastName, String patronymic, LocalDate dateBirth, String addressResidence,
                    Position position, String comment, List<PhoneNumber> phoneNumbers){
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.dateBirth = dateBirth;
        this.addressResidence = addressResidence;
        this.position = position;
        this.comment = comment;
        this.phoneNumbers = phoneNumbers;
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
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

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", dateBirth=" + dateBirth +
                ", addressResidence='" + addressResidence + '\'' +
                ", position=" + position +
                ", comment='" + comment + '\'' +
                ", phoneNumbers=" + phoneNumbers +
                '}';
    }

    public boolean isPhoneNumber(PhoneNumber phoneNumber){
        System.out.println(this.phoneNumbers);
        for(PhoneNumber thisPhoneNumber : this.phoneNumbers){
            if(thisPhoneNumber.isPhoneNumber(phoneNumber))
                return true;
        }
        return false;
    }

    //очень необычная реализация сравнения
    //Сделано так что бы не было разницы что вводить первее
    // фамилия имя или имя фамилия
    public boolean isName(String name) {
        String[] arrayName = name.split(" ");
        int i = 0;

        for(String firstName: arrayName){
            if(this.firstName.toLowerCase().equals(firstName.toLowerCase())) {
                i++;
                break;
            }
        }
        for(String lastName: arrayName) {
            if (this.lastName.toLowerCase().equals(lastName.toLowerCase())){
                i++;
                break;
            }
        }
        for(String patronymic: arrayName) {
            if (this.patronymic.toLowerCase().equals(patronymic.toLowerCase())){
                i++;
                break;
            }
        }
        return arrayName.length == i;
    }

    public void addPhoneNumber(PhoneNumber phoneNumber) {
        phoneNumber.setEmployee(this);
        phoneNumbers.add(phoneNumber);
    }

    public void removePhoneNumbers(PhoneNumber phoneNumber) {
        this.phoneNumbers.remove(phoneNumber);
    }
}
