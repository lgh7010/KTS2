package com.krafton.kts.frontend.test_rel_testcase.controller;

import com.krafton.kts.backend.test_rel_testcase.service.Service_test_rel_testcase;
import com.krafton.kts.frontend.common.ERROR_CODE;
import com.krafton.kts.frontend.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class Controller_test_rel_testcase {

    private final Service_test_rel_testcase service_test_rel_testcase;

    @Autowired
    public Controller_test_rel_testcase(Service_test_rel_testcase service_test_rel_testcase) {
        this.service_test_rel_testcase = service_test_rel_testcase;
    }

    @GetMapping("/testRelTestcaseList")
    @ResponseBody
    public Response testRelTestcaseList(
            HttpServletRequest req,
            @RequestParam(value = "TEST_SEQ") int TEST_SEQ,
            HttpServletResponse res){
        try {
            Response response = new Response();
            response.putContext("testRelTestcaseList", this.service_test_rel_testcase.findTestRelTestcaseByTEST_SEQ(TEST_SEQ));
            return response;
        } catch(Exception e){
            return new Response(ERROR_CODE.ERR_COMMON, e.getMessage());
        }
    }
}
