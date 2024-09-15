package com.ar.gallerion.exceptionbody;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

public class ApiRequestExceptionBody {
    private String message;
    private ZonedDateTime timestamp;
    private HttpStatus httpStatus;

    public ApiRequestExceptionBody(String message, ZonedDateTime timestamp,
            HttpStatus httpStatus) {
        this.message = message;
        this.timestamp = timestamp;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

}
