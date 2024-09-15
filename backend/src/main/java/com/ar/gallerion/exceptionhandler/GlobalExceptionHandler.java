package com.ar.gallerion.exceptionhandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import com.ar.gallerion.exception.ApiRequestException;
import com.ar.gallerion.exceptionbody.ApiRequestExceptionBody;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { ApiRequestException.class, MultipartException.class,
            MissingServletRequestPartException.class })
    public ResponseEntity<ApiRequestExceptionBody> handleApiRequestException(Exception e) {
        ApiRequestExceptionBody apiRequestExceptionBody = new ApiRequestExceptionBody(
                e.getMessage(),
                ZonedDateTime.now(ZoneId.of("Z")),
                HttpStatus.BAD_REQUEST);

        return ResponseEntity.badRequest().body(apiRequestExceptionBody);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleBadMethodArgumentException(MethodArgumentNotValidException e) {
        Map<String, String> errorFieldMap = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(err -> {
            errorFieldMap.put(err.getField(), err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errorFieldMap);
    }
}
