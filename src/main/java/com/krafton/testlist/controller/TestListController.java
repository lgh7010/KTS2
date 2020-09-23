package com.krafton.testlist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestListController {

    @GetMapping("/")
    public String showList(Model model){
        System.out.println("showList");
        return "testList";
    }
}
