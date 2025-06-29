package com.becoder.dto;

public class ErrorResponse {

    private Integer status;
    private String message;

    // Default constructor
    public ErrorResponse() {}

    // Private constructor for builder
    private ErrorResponse(Builder builder) {
        this.status = builder.status;
        this.message = builder.message;
    }

    // Getters and Setters
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // Builder class
    public static class Builder {

        private Integer status;
        private String message;

        public Builder status(Integer status) {
            this.status = status;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public ErrorResponse build() {
            return new ErrorResponse(this);
        }
    }
}
