package org.example.employeeapi.exception;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public class ErrorDetails {
    @Schema(description = "Timestamp of when the error occurred", example = "2023-10-10T15:30:12.123456")
    private LocalDateTime timestamp;

    @Schema(description = "Error message", example = "Employee not found with id 100")
    private String message;

    @Schema(description = "Additional details about the error", example = "uri=/api/employees/100")
    private String details;

    public ErrorDetails(LocalDateTime timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
