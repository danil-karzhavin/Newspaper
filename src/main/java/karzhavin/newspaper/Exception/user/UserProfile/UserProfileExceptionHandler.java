package karzhavin.newspaper.Exception.user.UserProfile;

import karzhavin.newspaper.Exception.user.UserDtoException;
import karzhavin.newspaper.Exception.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "karzhavin.newspaper.controller.user")
public class UserProfileExceptionHandler {
    @ExceptionHandler(UserProfileNotFoundException.class)
    public ResponseEntity<Object> userProfileNotFoundExceptionHandler(UserProfileNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(UserProfileDtoException.class)
    public ResponseEntity<Object> userProfileDtoExceptionHandler(UserProfileDtoException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
