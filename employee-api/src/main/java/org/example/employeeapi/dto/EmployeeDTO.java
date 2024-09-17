package org.example.employeeapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import java.time.LocalDate;


public class EmployeeDTO {

    private Long id;

    @NotBlank(message = "First name is mandatory")
    @Size(max = 50, message = "First name must be less than 50 characters")
    @Schema(description = "First name of the employee", example = "Alice")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Size(max = 50, message = "Last name must be less than 50 characters")
    @Schema(description = "Last name of the employee", example = "Smith")
    private String lastName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    @Schema(description = "Email address of the employee", example = "alice.smith@example.com")
    private String email;

    @NotBlank(message = "Position is mandatory")
    @Size(max = 100, message = "Position must be less than 100 characters")
    @Schema(description = "Position or job title of the employee", example = "Engineer")
    private String position;

    @PastOrPresent(message = "Hire date cannot be in the future")
    @Schema(description = "Hire date of the employee in ISO format", example = "2023-10-01")
    private LocalDate hireDate;

    public EmployeeDTO() {

    }

    public EmployeeDTO(Long id, String firstName, String lastName, String email, String position, LocalDate hireDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.position = position;
        this.hireDate = hireDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public  String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public  String getPosition() {
        return position;
    }

    public void setPosition( String position) {
        this.position = position;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

}