package com.abbascoban.model;


import lombok.*;

import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
public class Student extends BaseStudent{

    private String branch;
    private Integer note;

}
