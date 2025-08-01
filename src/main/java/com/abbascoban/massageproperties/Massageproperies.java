package com.abbascoban.massageproperties;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Massageproperies {

    @GetMapping("/massageproperties")
    public String massageProperties(){
        return "massagesthymeleaf";
    }
}
