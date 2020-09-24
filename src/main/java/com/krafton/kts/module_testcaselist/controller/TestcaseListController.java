package com.krafton.kts.module_testcaselist.controller;

import com.krafton.kts.module_testcaselist.domain.KTS_TESTCASE;
import com.krafton.kts.module_testcaselist.service.TestcaseListService;
import com.krafton.kts.module_testlist.domain.KTS_TEST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestcaseListController {

    private final TestcaseListService testcaseListService;

    @Autowired
    public TestcaseListController(TestcaseListService testcaseListService){
        this.testcaseListService = testcaseListService;
    }

    @PostMapping("/testcaseList")
    @ResponseBody
    public List<KTS_TESTCASE> testcaseList(String TEST_SEQ){
        try {
            return this.testcaseListService.findTestcaseByTEST_SEQ(Integer.parseInt(TEST_SEQ));
        } catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
}
