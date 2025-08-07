package com.abbascoban.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Exception<T> {

    private String pathName;

    private String hostName;

    private Date creatTime;

    private T message;

}
