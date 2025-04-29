package com.ecommerce.common.exception;

public class CustomException extends RuntimeException {
    private final String errorCode;
    private final String suggestedAction;

    public CustomException(String message, String errorCode, String suggestedAction) {
        super(message);
        this.errorCode = errorCode;
        this.suggestedAction = suggestedAction;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getSuggestedAction() {
        return suggestedAction;
    }
}
