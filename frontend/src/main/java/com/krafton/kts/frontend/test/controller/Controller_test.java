package com.krafton.kts.frontend.test.controller;

import com.krafton.kts.backend.test.domain.KTS_TEST;
import com.krafton.kts.backend.test.service.Service_test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class Controller_test {

    private final Service_test testListService;

    @Autowired
    public Controller_test(Service_test testListService){
        this.testListService = testListService;
    }

    @GetMapping("/testList")
    @ResponseBody
    public List<KTS_TEST> testListOnly(){
        return this.testListService.findAll();
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
