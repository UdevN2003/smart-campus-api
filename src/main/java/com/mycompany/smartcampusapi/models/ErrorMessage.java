package com.mycompany.smartcampusapi.models;

public class ErrorMessage {
    private String error;

    public ErrorMessage() {} // Empty constructor required for JSON

    public ErrorMessage(String error) {
        this.error = error;
    }

    public String getError() { return error; }
    public void setError(String error) { this.error = error; }
}