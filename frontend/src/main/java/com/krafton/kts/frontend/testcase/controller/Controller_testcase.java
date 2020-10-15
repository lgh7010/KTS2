package com.krafton.kts.frontend.testcase.controller;

import com.krafton.kts.backend.testcase.domain.KTS_TESTCASE;
import com.krafton.kts.backend.testcase.service.Service_testcase;
import com.krafton.kts.frontend.common.ERROR_CODE;
import com.krafton.kts.frontend.common.Response;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
public class Controller_testcase {

    private final Service_testcase service_testcase;

    @Autowired
    public Controller_testcase(Service_testcase testcaseListService){
        this.service_testcase = testcaseListService;
    }

    @GetMapping("/testcaseList")
    @ResponseBody
    public Response testcaseList(
            HttpServletRequest req,
            HttpServletResponse res
    ){
        try {
            Response response = new Response();
            response.putContext("testcaseList", this.service_testcase.findAll());
            return response;
        } catch(Exception e){
            return new Response(ERROR_CODE.ERR_COMMON, e.getMessage());
        }
    }

    @GetMapping("/testcaseDic")
    @ResponseBody
    public Response testcaseDic(
            HttpServletRequest req,
            @RequestParam(value = "TEST_SEQ") int TEST_SEQ,
            HttpServletResponse res
    ){
        try {
            List<KTS_TESTCASE> list = this.service_testcase.findTestcasesByTEST_SEQ(TEST_SEQ);
            Map<Integer, KTS_TESTCASE> ret = new HashMap<>();
            for (Iterator<KTS_TESTCASE> iter = list.iterator(); iter.hasNext();){
                KTS_TESTCASE tc = iter.next();
                ret.put(tc.getTESTCASE_SEQ(), tc);
            }

            Response response = new Response();
            response.putContext("testcaseDic", ret);
            return response;
        } catch(Exception e) {
            return new Response(ERROR_CODE.ERR_COMMON, e.getMessage());
        }
    }

    @PostMapping("/removeTestcase")
    @ResponseBody
    public Response removeTestcase(
            HttpServletRequest req,
            @RequestBody String requestJsonStr,
            HttpServletResponse res
    ){
        try {
            JSONObject requestJsonObj = new JSONObject(requestJsonStr);
            this.service_testcase.removeTestcase(requestJsonObj.getInt("TESTCASE_SEQ"));
            return new Response();
        } catch(Exception e){
            return new Response(ERROR_CODE.ERR_COMMON, e.getMessage());
        }
    }
}
