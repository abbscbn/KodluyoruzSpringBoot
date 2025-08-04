package com.abbascoban.pathvariable;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PathVariableTyhmeleaf {


    @GetMapping({"/pathvariable/{id}","/pathvariable","/pathvariable/"})
    public String pathVariable(Model model, @PathVariable(name = "id", required = false) Long id){

        if(id!=null){
            model.addAttribute("path_key","number of id: "+id);
        }
        else{
            model.addAttribute("path_key","id is not found");
        }


        return "pathvariablethymeleaf";

    }
}
