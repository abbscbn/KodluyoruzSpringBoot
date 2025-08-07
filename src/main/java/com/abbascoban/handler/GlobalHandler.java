package com.abbascoban.handler;

import com.abbascoban.exception.BaseExcepiton;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

@ControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(value = {BaseExcepiton.class})
    public ResponseEntity<ApiError> handlerBaseException(BaseExcepiton ex, WebRequest request){
        return ResponseEntity.badRequest().body(creatApiError(ex.getMessage(),request));
    }


    public <T> ApiError<T> creatApiError(T massage, WebRequest request){

        Exception<T> exception= new Exception<>();
        ApiError<T> apiError= new ApiError<>();

        exception.setCreatTime(new Date());
        exception.setPathName(request.getDescription(false).substring(4));
        exception.setMessage(massage);
        exception.setHostName(getHostName());

        apiError.setStatus(HttpStatus.BAD_REQUEST.value());

        apiError.setException(exception);

        return apiError;

    }

    public String getHostName(){
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            System.out.println("Could not get host name"+ e.getMessage());
        }
        return null;
    }

}
