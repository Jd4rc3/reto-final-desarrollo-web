package org.sofka.mykrello.controller;

import org.sofka.mykrello.utilities.MyResponseUtility;
import org.sofka.mykrello.utilities.exceptions.MismatchDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @Autowired
    MyResponseUtility response;

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<MyResponseUtility> runtimeException(RuntimeException e) {
        response.setFields(true, e.getMessage(), null);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = MismatchDataException.class)
    public ResponseEntity<MyResponseUtility> mismatchDataException(MismatchDataException e) {
        response.setFields(true, e.getMessage(), null);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}