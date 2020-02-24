package com.julien.hansab.rest.error;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class AppExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(AppExceptionHandler.class);

    @ExceptionHandler(Throwable.class)
    protected ResponseEntity<Object> handleOtherExceptions(Throwable t) {
        log.error(t.toString());
        return buildResponseEntity(new AppError(INTERNAL_SERVER_ERROR, "Uknown server error!",
                LocalDateTime.now()));
    }

    @ExceptionHandler(javax.persistence.EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException e) {
        log.error(e.toString());
        return buildResponseEntity(new AppError(BAD_REQUEST, "Entity not found!", LocalDateTime.now()));
    }

    private ResponseEntity<Object> buildResponseEntity(AppError appError) {
        return new ResponseEntity<>(appError, appError.getStatus());
    }
}
