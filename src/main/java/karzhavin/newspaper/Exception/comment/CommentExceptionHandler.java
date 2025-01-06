package karzhavin.newspaper.Exception.comment;

import karzhavin.newspaper.Exception.user.UserDtoException;
import karzhavin.newspaper.Exception.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("karzhavin.newspaper.controller.comment")
public class CommentExceptionHandler {
    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<Object> commentNotFoundExceptionHandler(CommentNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(CommentDtoException.class)
    public ResponseEntity<Object> commentDtoExceptionHandler(CommentDtoException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
