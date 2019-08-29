package com.servece;

import com.dao.EmployeeDAO;
import com.entity.Employee;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    private EmployeeDAO employeeDAO = new EmployeeDAO();
    public EmployeeService() {
    }
    public Employee findEmployee(int id) {
        return employeeDAO.findById(id);
    }

    public void saveEmployee(Employee employee) {
        employeeDAO.save(employee);
    }

    public void deleteEmployee(Employee employee) {
        employeeDAO.delete(employee);
    }

    public void updateEmployee(Employee employee) {
        employeeDAO.update(employee);
    }

    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        for(Object employee :employeeDAO.getAllEmployee()){
            list.add((Employee) employee);
        }
        return list;
    }



}
