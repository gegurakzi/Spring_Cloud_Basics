package kr.co.tmax.bi.web.browser.handler;

import io.micrometer.observation.annotation.Observed;
import kr.co.tmax.bi.web.common.exception.EmailAlreadyExistsException;
import kr.co.tmax.bi.web.common.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestControllerAdvice
public class UserServiceExceptionHandler {
    private static Logger LOG = Logger.getLogger(UserServiceExceptionHandler.class.getName());

    @ExceptionHandler(UserNotFoundException.class)
    @Observed(name = "browser.email-already-exists-exception-handler")
    public ResponseEntity<?> userNotFoundExceptionHandler(UserNotFoundException e) {
        LOG.log(Level.WARNING, e.getClass() + " : userId=" + e.getMessage());
        return new ResponseEntity<>("user not found: "+e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    @Observed(name = "browser.email-already-exists-exception-handler")
    public ResponseEntity<?> emailAlreadyExistsExceptionHandler(EmailAlreadyExistsException e) {
        LOG.log(Level.WARNING, e.getClass() + " : email=" + e.getMessage());
        return new ResponseEntity<>("email already exists: "+e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

}
