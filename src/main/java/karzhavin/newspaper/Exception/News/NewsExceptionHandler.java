package karzhavin.newspaper.Exception.News;

import karzhavin.newspaper.Exception.user.UserDtoException;
import karzhavin.newspaper.Exception.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("karzhavin.newspaper.controller.news")
public class NewsExceptionHandler {
    @ExceptionHandler(NewsNotFoundException.class)
    public ResponseEntity<Object> newsNotFoundExceptionHandler(NewsNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(NewsDtoException.class)
    public ResponseEntity<Object> newsDtoExceptionHandler(NewsDtoException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
