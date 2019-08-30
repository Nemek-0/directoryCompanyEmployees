package com.entity;

import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;


public class EmployeeTest {

    @Test
    public void isPhoneNumber() {
        List<PhoneNumber> list = new ArrayList<PhoneNumber>();
        list.add(new PhoneNumber("79201203110"));
        list.add(new PhoneNumber("79201455878"));

        Employee employee = new Employee();
        employee.setPhoneNumbers(list);

        Assert.assertTrue(employee.isPhoneNumberOwnedEmployee(new PhoneNumber("79201203110")));
        Assert.assertTrue(employee.isPhoneNumberOwnedEmployee(new PhoneNumber("79201455878")));
        Assert.assertFalse(employee.isPhoneNumberOwnedEmployee(new PhoneNumber("79207899685")));
    }

    @Test
    public void isName() {
        Employee employee = new Employee("Кузнецов", "Сергей", "Михайлович");

        Assert.assertTrue(employee.isName("Сергей"));
        Assert.assertTrue(employee.isName("Кузнецов"));
        Assert.assertTrue(employee.isName("Михайлович"));
        Assert.assertTrue(employee.isName("Кузнецов Сергей"));
        Assert.assertTrue(employee.isName("Сергей Кузнецов"));
        Assert.assertTrue(employee.isName("Кузнецов Михайлович"));
        Assert.assertTrue(employee.isName("Михайлович Кузнецов"));
        Assert.assertTrue(employee.isName("Михайлович Сергей"));
        Assert.assertTrue(employee.isName("Сергей Михайлович"));
        Assert.assertTrue(employee.isName("Кузнецов Сергей Михайлович"));
        Assert.assertTrue(employee.isName("Михайлович Кузнецов Сергей "));
        Assert.assertTrue(employee.isName("сергей"));
        Assert.assertTrue(employee.isName("михайлович кузнецов сергей"));
        Assert.assertFalse(employee.isName("Кузнец"));
        Assert.assertFalse(employee.isName("Андрей"));
        Assert.assertFalse(employee.isName("Андрей Кузнецов"));
        Assert.assertFalse(employee.isName("Андрей Кузнецов Михайлович"));

    }

    @Test
    public void addPhoneNumber() {
    }

    @Test
    public void removePhoneNumbers() {
    }
}