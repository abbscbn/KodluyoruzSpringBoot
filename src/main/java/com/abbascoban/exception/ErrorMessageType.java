package com.abbascoban.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum ErrorMessageType {

    GENEREAL_EXCEPTION(1001,"General Error"),
    NOT_FOUND(1002,"NOT FOUND");

    private Integer code;

    private String description;

    ErrorMessageType(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
