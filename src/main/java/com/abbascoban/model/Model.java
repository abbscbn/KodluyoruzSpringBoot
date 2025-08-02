package com.abbascoban.model;


import lombok.*;

import lombok.experimental.SuperBuilder;
import org.springframework.context.annotation.Scope;

@Getter
@Setter
@SuperBuilder
public class Model extends BaseModel{

    private String branch;

}
