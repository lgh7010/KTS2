package com.krafton.kts.testlist.controller;

import com.krafton.kts.repository.testinfo.TestInfoRepo;
import com.krafton.kts.testlist.domain.KTS_TEST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestListController {

    private TestInfoRepo testInfoRepo;

    @Autowired
    public TestListController(TestInfoRepo testInfoRepo){
        this.testInfoRepo = testInfoRepo;
    }

    @GetMapping("/")
    public String showList(Model model){

        List<KTS_TEST> tests = this.testInfoRepo.findAll();
        System.out.println(tests.stream().count());
        model.addAttribute("tests", tests);

        return "testlist/testList";
    }

    @GetMapping("/create")
    public String showCreate(Model model){
        return "testedit/testEdit";
    }
}
