package com.abbascoban.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {

    private ErrorMessageType errorMessageType;

    private String ofStatic;

    public String prepareErrorMessage(){

        StringBuilder stringBuilder= new StringBuilder();
        stringBuilder.append("Error Code: " +errorMessageType.getCode()+" Description: "+errorMessageType.getDescription());
        stringBuilder.append(" "+ofStatic);

        return stringBuilder.toString();
    }


}
