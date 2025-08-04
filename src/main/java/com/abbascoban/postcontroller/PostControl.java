package com.abbascoban.postcontroller;

import com.abbascoban.validpostandget.Teacher;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Log4j2
public class PostControl {

    @GetMapping("/form")
    public String getForm(Model model){
        model.addAttribute("teacher",new Teacher());
        return "postformthymeleaf";
    }


    @PostMapping("/form")
    public String postForm(@Valid @ModelAttribute("teacher") Teacher teacher, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.error("hata var");
            System.err.println("Hata var");
            return "postformthymeleaf";
        }

        System.out.println(teacher.getTeacherNameSurname()+" "+teacher.getTeacherEmail()+" "+teacher.getTeacherPassword());

        return "successformthymeleaf";

    }
}
