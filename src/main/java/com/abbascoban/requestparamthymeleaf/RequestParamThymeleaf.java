package com.abbascoban.requestparamthymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RequestParamThymeleaf {

    @GetMapping("/requestparam")
    public String requestParam(Model model,
                               @RequestParam(name = "id",required = false, defaultValue = "-1") Long id,
                               @RequestParam(name = "name", required = false, defaultValue = "null") String name,
                               @RequestParam(name = "surname", required = false, defaultValue = "null") String surname){

        model.addAttribute("requestparam","id: "+id+" name: "+name+" surname: "+surname);

        return "requestparamthymeleaf";

    }
}
