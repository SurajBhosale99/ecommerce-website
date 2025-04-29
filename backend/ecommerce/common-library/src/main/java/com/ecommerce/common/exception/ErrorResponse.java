package com.ecommerce.common.exception;

public class ErrorResponse {

    private String errorMessage;
    private String errorCode;
    private String suggestedAction;
    private String path;
    private int status;

    public ErrorResponse(String errorMessage, String errorCode, String suggestedAction, String path, int status) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.suggestedAction = suggestedAction;
        this.path = path;
        this.status = status;
    }

    // Getters and Setters
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getSuggestedAction() {
        return suggestedAction;
    }

    public void setSuggestedAction(String suggestedAction) {
        this.suggestedAction = suggestedAction;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}