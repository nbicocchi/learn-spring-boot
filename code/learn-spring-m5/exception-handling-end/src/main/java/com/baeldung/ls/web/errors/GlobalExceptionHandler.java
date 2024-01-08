package com.baeldung.ls.web.errors;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataRetrievalFailureException.class)
    public ProblemDetail handleDataRetrievalException(DataRetrievalFailureException ex) {
        ProblemDetail problemDetails = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
        return problemDetails;
    }
}
