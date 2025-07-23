package com.streamix.common.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends RuntimeException {
    private String message;
    private String code;
    private final HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    public NotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public NotFoundException(String code, String message) {
        super(message);
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
