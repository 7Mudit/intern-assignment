package org.example.employeeservice.service.impl;

import org.example.employeecommon.exception.ResourceNotFoundException;
import org.example.employeeservice.entity.Employee;
import org.example.employeeservice.repository.EmployeeRepository;
import org.example.employeeservice.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = getEmployeeById(id);
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setPosition(employeeDetails.getPosition());
        employee.setHireDate(employeeDetails.getHireDate());

        return employeeRepository.save(employee);
    }

    @Override
    public Employee partialUpdateEmployee(Long id, Employee employeeDetails) {
        Employee employee = getEmployeeById(id);

        if(employeeDetails.getFirstName() != null){
            employee.setFirstName(employeeDetails.getFirstName());
        }

        if(employeeDetails.getLastName() != null){
            employee.setLastName(employeeDetails.getLastName());
        }

        if(employeeDetails.getEmail() != null){
            employee.setEmail(employeeDetails.getEmail());
        }

        if(employeeDetails.getPosition() != null){
            employee.setPosition(employeeDetails.getPosition());
        }

        if(employeeDetails.getHireDate() != null){
            employee.setHireDate(employeeDetails.getHireDate());
        }

        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee= getEmployeeById(id);
        employeeRepository.delete(employee);
    }

}
