package com.krafton.kts.frontend.test_rel_testcase.controller;

import com.krafton.kts.backend.test_rel_testcase.domain.TEST_REL_TESTCASE;
import com.krafton.kts.backend.test_rel_testcase.service.Service_test_rel_testcase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class Controller_test_rel_testcase {

    private final Service_test_rel_testcase service_test_rel_testcase;

    @Autowired
    public Controller_test_rel_testcase(Service_test_rel_testcase service_test_rel_testcase) {
        this.service_test_rel_testcase = service_test_rel_testcase;
    }

    @PostMapping("/testRelTestcaseList")
    @ResponseBody
    public List<TEST_REL_TESTCASE> testRelTestcaseList(String TEST_SEQ){
        try {
            return this.service_test_rel_testcase.findTestRelTestcaseByTEST_SEQ(Integer.parseInt(TEST_SEQ));
        } catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
}
