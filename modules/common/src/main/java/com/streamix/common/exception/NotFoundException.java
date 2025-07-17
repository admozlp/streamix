package com.streamix.common.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class NotFoundException extends RuntimeException {
    private String message;
    private Object data;
    private HttpStatus httpStatus;
    private LocalDateTime eventTime;

    public NotFoundException(String message) {
        super(message);
        this.message = message;
        this.data = null;
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.eventTime = LocalDateTime.now();
    }

    public NotFoundException(String message, Object data) {
        super(message);
        this.message = message;
        this.data = data;
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.eventTime = LocalDateTime.now();
    }

    public NotFoundException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.data = null;
        this.httpStatus = httpStatus;
        this.eventTime = LocalDateTime.now();
    }

    public NotFoundException(String message, Object data, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.data = data;
        this.httpStatus = httpStatus;
        this.eventTime = LocalDateTime.now();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }
}
