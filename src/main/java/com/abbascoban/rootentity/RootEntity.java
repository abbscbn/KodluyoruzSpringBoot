package com.abbascoban.rootentity;

import com.abbascoban.handler.Exception;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RootEntity<T> {

    private Boolean result;

    private Integer status;

    private String pathName;

    private String hostName;

    private Date creatTime;

    private Exception<T> exceptionMessage;

    private T data;

    public static <T> RootEntity<T> ok(T data, WebRequest request){
        RootEntity<T> rootEntity= new RootEntity<>();
        rootEntity.setHostName(getHostName());
        rootEntity.setPathName(request.getDescription(false));
        rootEntity.setCreatTime(new Date());
        rootEntity.setResult(true);
        rootEntity.setStatus(HttpStatus.OK.value());
        rootEntity.setData(data);
        rootEntity.setExceptionMessage(null);
        return rootEntity;
    }

    public static <T> RootEntity<T> error(T error, WebRequest request){
        Exception<T> exception= new Exception<>();
        RootEntity<T> rootEntity= new RootEntity<>();
        rootEntity.setHostName(getHostName());
        rootEntity.setPathName(request.getDescription(false));
        rootEntity.setCreatTime(new Date());
        rootEntity.setResult(false);
        rootEntity.setStatus(HttpStatus.BAD_REQUEST.value());
        rootEntity.setData(null);
        exception.setMessage(error);
        rootEntity.setExceptionMessage(exception);
        return rootEntity;
    }

    public static <T> RootEntity<T> error(T error){
        Exception<T> exception= new Exception<>();
        RootEntity<T> rootEntity= new RootEntity<>();
        rootEntity.setHostName(getHostName());
        rootEntity.setPathName(null);
        rootEntity.setCreatTime(new Date());
        rootEntity.setResult(false);
        rootEntity.setStatus(HttpStatus.UNAUTHORIZED.value());
        rootEntity.setData(null);
        exception.setMessage(error);
        rootEntity.setExceptionMessage(exception);
        return rootEntity;
    }




    public static String getHostName(){
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            System.out.println("Could not get host name"+ e.getMessage());
        }
        return null;
    }

}




