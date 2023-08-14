package kr.co.tmax.bi.web.user.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class SqlExceptionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<?> constraintViolationHandler(SQLIntegrityConstraintViolationException e) {
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

}
