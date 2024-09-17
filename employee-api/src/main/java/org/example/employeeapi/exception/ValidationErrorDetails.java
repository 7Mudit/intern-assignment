package org.example.employeeapi.exception;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.Map;

public class ValidationErrorDetails {
    @Schema(description = "Timestamp of when the error occurred", example = "2023-10-10T15:30:12.123456")
    private LocalDateTime timestamp;

    @Schema(description = "General error message", example = "Validation Failed")
    private String message;

    @Schema(description = "Map of field names to error messages")
    private Map<String, String> errors;

    public ValidationErrorDetails(LocalDateTime timestamp, String message, Map<String, String> errors) {
        this.timestamp = timestamp;
        this.message = message;
        this.errors = errors;
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

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
