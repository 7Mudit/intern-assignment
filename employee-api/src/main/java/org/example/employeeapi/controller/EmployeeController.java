package org.example.employeeapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.employeeapi.dto.EmployeeDTO;
import org.example.employeeapi.exception.ErrorDetails;
import org.example.employeeapi.exception.ValidationErrorDetails;
import org.example.employeeservice.entity.Employee;
import org.example.employeeservice.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Tag(name = "Employee Management", description = "Operations related to employees")
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final ModelMapper modelMapper;

    public EmployeeController(EmployeeService employeeService , ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }


    @Operation(summary = "Create a new employee", description = "Adds a new employee to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorDetails.class)))
    })
    @PostMapping("/create")
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid  @RequestBody EmployeeDTO employeeDTO) {
        Employee employeeRequest = modelMapper.map(employeeDTO, Employee.class);
        Employee employee = employeeService.createEmployee(employeeRequest);
        EmployeeDTO employeeResponse = modelMapper.map(employee, EmployeeDTO.class);
        return new ResponseEntity<>(employeeResponse , HttpStatus.CREATED);
    }

    @Operation(summary = "Get an employee by ID", description = "Retrieves an employee by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeDTO.class))),
            @ApiResponse(responseCode = "404", description = "Employee not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        EmployeeDTO employeeResponse = modelMapper.map(employee, EmployeeDTO.class);
        return ResponseEntity.ok(employeeResponse);
    }

    @Operation(summary = "Get all employees", description = "Retrieves a list of all employees")
    @ApiResponse(responseCode = "200", description = "List of employees retrieved successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EmployeeDTO.class)))
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        List<EmployeeDTO> employeeDTOS = employees.stream().map(employee -> modelMapper.map(employee , EmployeeDTO.class)).toList();
        return ResponseEntity.ok(employeeDTOS);
    }

    @Operation(summary = "Update an existing employee", description = "Updates the details of an existing employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorDetails.class))),
            @ApiResponse(responseCode = "404", description = "Employee not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeDTO employeeDTO) {
        Employee employeeRequest = modelMapper.map(employeeDTO, Employee.class);
        Employee employee = employeeService.updateEmployee(id, employeeRequest);
        EmployeeDTO employeeResponse = modelMapper.map(employee, EmployeeDTO.class);
        return ResponseEntity.ok(employeeResponse);
    }

    @Operation(summary = "Partially update an employee", description = "Updates some details of an existing employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorDetails.class))),
            @ApiResponse(responseCode = "404", description = "Employee not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class)))
    })
    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeDTO> patchEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        Employee employeeRequest = modelMapper.map(employeeDTO, Employee.class);
        Employee employee = employeeService.partialUpdateEmployee(id, employeeRequest);
        EmployeeDTO employeeResponse = modelMapper.map(employee, EmployeeDTO.class);
        return ResponseEntity.ok(employeeResponse);
    }

    @Operation(summary = "Delete an employee", description = "Deletes an employee by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Employee deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Employee not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeDTO> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
