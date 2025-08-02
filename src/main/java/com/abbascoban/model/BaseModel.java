package com.abbascoban.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class BaseModel {

    private Long id;
    private String name;
    private String surname;



}
