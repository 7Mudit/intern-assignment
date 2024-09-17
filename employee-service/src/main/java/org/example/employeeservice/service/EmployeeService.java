package org.example.employeeservice.service;

import org.example.employeeservice.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    Employee getEmployeeById(Long id);
    List<Employee> getAllEmployees();
    Employee updateEmployee(Long id , Employee employeeDetails);
    Employee partialUpdateEmployee(Long id, Employee employeeDetails);
    void deleteEmployee(Long id);
}
