package com.krafton.kts.module_testedit.controller;

import com.krafton.kts.module_testlist.domain.KTS_TEST;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TestEditController {

    @PostMapping("/editTest")
    public String testEdit(KTS_TEST test){
        System.out.println("testEdit : " + test.getTEST_SEQ());
        return "testedit/testEdit";
    }
}
