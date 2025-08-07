package com.abbascoban.handler;

import com.abbascoban.exception.BaseExcepiton;
import com.abbascoban.rootentity.RootEntity;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.*;

@ControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(value = {BaseExcepiton.class})
    public ResponseEntity<RootEntity<String>> handlerBaseException(BaseExcepiton ex, WebRequest request){
        return ResponseEntity.badRequest().body(RootEntity.error(ex.getMessage(),request));
    }

    public ArrayList<String> addToErrorMessageList(ArrayList<String> ErrorList,String message){
        ErrorList.add(message);
        return ErrorList;
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<RootEntity<Map<String, ArrayList<String>>>> handlerValidationException(ConstraintViolationException ex, WebRequest request){

        Map<String, ArrayList<String>> errorList= new HashMap<>();


        for(ConstraintViolation c: ex.getConstraintViolations()){

            String fieldName = c.getPropertyPath().toString();


            if(errorList.containsKey(fieldName)){
                errorList.put(fieldName,addToErrorMessageList(errorList.get(fieldName),c.getMessage()));
            }
            else{
                errorList.put(fieldName,addToErrorMessageList(new ArrayList<>(),c.getMessage()));
            }


        }

        return  ResponseEntity.badRequest().body(RootEntity.error(errorList,request));


    }



}
