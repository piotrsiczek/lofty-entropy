package com.spiczek.chat.shared.errors;

import java.io.Serializable;

/**
 * @author Piotr Siczek
 */
public class ServiceError extends Exception implements Serializable {

    private String errorMessage;

    public ServiceError() {}

    public ServiceError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
