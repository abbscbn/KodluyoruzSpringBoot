package com.abbascoban.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Thymeleaf {


    @GetMapping("/thymeleaf")
    public String Thymeleaf(){
        return "thymeleaf";
    }

}
