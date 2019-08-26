package com.entity;

import javax.persistence.*;

@Entity
@Table(name = "phonenumbers")
public class PhoneNumber {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeeId")
    private Employee employee;

    private String typePhoneNumber;
    private String phoneNumber;


    public PhoneNumber() {
    }
    public PhoneNumber(String typePhoneNumber, String phoneNumber) {
        this.typePhoneNumber = typePhoneNumber;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getTypePhoneNumber() {
        return typePhoneNumber;
    }

    public void setTypePhoneNumber(String typePhoneNumber) {
        this.typePhoneNumber = typePhoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
