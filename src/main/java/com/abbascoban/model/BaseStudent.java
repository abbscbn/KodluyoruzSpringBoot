package com.abbascoban.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class BaseStudent {

    private Long id;
    private String name;
    private String surname;


}
