package com.krafton.kts.frontend.domain.testcase.controller;

import com.krafton.kts.backend.cross.CrossService;
import com.krafton.kts.backend.domain.testcase.service.TestcaseService;
import com.krafton.kts.frontend.common.ErrorCode;
import com.krafton.kts.frontend.common.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class TestcaseController {

    private final TestcaseService testcaseService;
    private final CrossService systemService;

    @GetMapping("/testcaseList")
    @ResponseBody
    public Response testcaseList(){
        try {
            Response response = new Response();
            response.putContext("testcaseList", this.testcaseService.findAll());
            return response;
        } catch(Exception e){
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
        }
    }

    @GetMapping("/testcase")
    @ResponseBody
    public Response testcase(@RequestParam(value = "testcaseGuid") String testcaseGuid){
        try {
            Response response = new Response();
            response.putContext("testcase", this.testcaseService.findTestcase(testcaseGuid));
            return response;
        } catch (Exception e){
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
        }
    }
}
