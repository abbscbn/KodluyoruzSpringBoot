package com.abbascoban.modeltymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class modelthymeleaf {

    @GetMapping("/modelthymeleaf")
    public String modeltyhmeleaf(Model model){
        model.addAttribute("model_key1","modelden gelen veri1");
        model.addAttribute("model_key2","modelden gelen veri2");
        return "modelthymeleaf"; //burada template kısımındaki dosya ismini gönderiyoruz eğer dizin altındaysa ona göre ayar yapıyoruz
    }
}
