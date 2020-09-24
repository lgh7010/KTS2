package com.krafton.kts.module_testedit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TestEditController {

    @PostMapping("/editTest")
    public String testEdit(String clickIdx){
        System.out.println("testEdit : " + clickIdx);
        return "testedit/testEdit";
    }
}
