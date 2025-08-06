package com.abbascoban.handler;

import com.abbascoban.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<String> handlerBaseException(ResourceNotFoundException ex, WebRequest request){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
