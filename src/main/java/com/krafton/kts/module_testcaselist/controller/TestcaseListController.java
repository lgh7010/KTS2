package com.krafton.kts.module_testcaselist.controller;

import com.krafton.kts.module_testcaselist.domain.KTS_TESTCASE;
import com.krafton.kts.module_testlist.domain.KTS_TEST;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestcaseListController {

    @PostMapping("/testcaseList")
    @ResponseBody
    public List<KTS_TESTCASE> testcaseList(KTS_TEST test, Model model){

        List<KTS_TESTCASE> list = new ArrayList<>();
        KTS_TESTCASE temp = new KTS_TESTCASE();

        temp.setTESTCASE_SEQ(1);
        temp.setNAME("TC1");
        temp.setDESCRIPTION("DC1");
        list.add(temp);

        return list;
    }
}
