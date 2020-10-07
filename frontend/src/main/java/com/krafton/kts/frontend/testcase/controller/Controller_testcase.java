package com.krafton.kts.frontend.testcase.controller;

import com.krafton.kts.backend.testcase.domain.KTS_TESTCASE;
import com.krafton.kts.backend.testcase.service.Service_testcase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class Controller_testcase {

    private final Service_testcase testcaseListService;

    @Autowired
    public Controller_testcase(Service_testcase testcaseListService){
        this.testcaseListService = testcaseListService;
    }

    @PostMapping("/testcaseList")
    @ResponseBody
    public List<KTS_TESTCASE> testcaseList(String TEST_SEQ){
        try {
            return this.testcaseListService.findTestcasesByTEST_SEQ(Integer.parseInt(TEST_SEQ));
        } catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @PostMapping("/testcaseDic")
    @ResponseBody
    public Map<Integer, KTS_TESTCASE> testcaseDic(String TEST_SEQ){
        try {
            List<KTS_TESTCASE> list = this.testcaseListService.findTestcasesByTEST_SEQ(Integer.parseInt(TEST_SEQ));
            Map<Integer, KTS_TESTCASE> ret = new HashMap<>();
            for (Iterator<KTS_TESTCASE> iter = list.iterator(); iter.hasNext();){
                KTS_TESTCASE tc = iter.next();
                ret.put(tc.getTESTCASE_SEQ(), tc);
            }
            return ret;
        } catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
