package com.abbascoban.exception;

import java.io.Serializable;

public class BaseExcepiton extends RuntimeException implements Serializable {

    public BaseExcepiton() {

    }
    public BaseExcepiton(ErrorMessage errorMessage){
        super(errorMessage.prepareErrorMessage());
    }
}
