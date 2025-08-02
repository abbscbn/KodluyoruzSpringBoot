package com.abbascoban.modellist;

import com.abbascoban.model.BaseStudent;
import com.abbascoban.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;


@Controller
public class ModelList {

    @GetMapping("/listmodel")
    public String getListModel(Model model){

        ArrayList<Student> students= new ArrayList<>();

        students.add(Student.builder().id(1L).name("ABBAS").surname("ÇOBAN").branch("YAZILIM").note(65).build());
        students.add(Student.builder().id(2L).name("NAZLI").surname("ÇOBAN").branch("İK").note(45).build());

        model.addAttribute("model_list",students);

        return "modellistthymeleaf";

    }
}
