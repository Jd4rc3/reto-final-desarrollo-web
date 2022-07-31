package org.sofka.mykrello.controller;

import org.sofka.mykrello.utilities.MyResponseUtility;
import org.sofka.mykrello.utilities.exceptions.MismatchDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ControllerAdvice class is used to handle exceptions thrown by the both services with custom
 * response field injection.
 *
 * @author LuisaAvila <luisaavila304@gmail.com>
 */
@RestControllerAdvice
public class ControllerAdvice {
    /**
     * Response object to handle same responses.
     */
    @Autowired
    MyResponseUtility response;

    /**
     * ExceptionHandler method is used to handle generic exceptions thrown by the both services.
     *
     * @param e the exception thrown.
     * @return ResponseEntity<MyResponseUtility> the custom response.
     */
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<MyResponseUtility> runtimeException(RuntimeException e) {
        response.setFields(true, e.getMessage(), null);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * ExceptionHandler method is used to handle MismatchDataException thrown by the both services.
     *
     * @param e the exception thrown.
     * @return ResponseEntity<MyResponseUtility> the custom response.
     */
    @ExceptionHandler(value = MismatchDataException.class)
    public ResponseEntity<MyResponseUtility> mismatchDataException(MismatchDataException e) {
        response.setFields(true, e.getMessage(), null);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}