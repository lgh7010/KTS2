package com.krafton.kts.module_testlist.controller;

import com.krafton.kts.module_repository.testinfo.TestInfoRepo;
import com.krafton.kts.module_testlist.domain.KTS_TEST;
import com.krafton.kts.module_testlist.service.TestListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TestListController {

    private final TestListService testListService;

    @Autowired
    public TestListController(TestListService testListService){
        this.testListService = testListService;
    }

    @GetMapping("/")
    public String showList(Model model){
        model.addAttribute("tests", this.testListService.findAll());
        return "testlist/testList";
    }

    @PostMapping("/removeTest")
    public String removeTest(String clickIdx, Model model){
        System.out.println("removeTest : " + clickIdx);
        return showList(model);
    }
}
