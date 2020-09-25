package com.krafton.kts.frontend.testlist.controller;

import com.krafton.kts.backend.testlist.service.TestListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestListController {

    private final TestListService testListService;

    @Autowired
    public TestListController(TestListService testListService){
        this.testListService = testListService;
    }

    @GetMapping("/")
    public String showList(Model model){
        System.out.println("ttttttttt");
        model.addAttribute("tests", this.testListService.findAll());
        return "testlist/testList";
    }

    @PostMapping("/removeTest")
    @ResponseBody
    public String removeTest(String TEST_SEQ, Model model){
        try {
            this.testListService.removeTest(Integer.parseInt(TEST_SEQ));
            return "removeTest Success";
        } catch(Exception e){
            System.out.println(e);
            return e.getMessage();
        }
    }
}
