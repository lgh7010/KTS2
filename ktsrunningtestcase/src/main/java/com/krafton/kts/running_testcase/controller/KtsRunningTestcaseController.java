package com.krafton.kts.running_testcase.controller;

import com.krafton.kts.common.ErrorCode;
import com.krafton.kts.common.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class KtsRunningTestcaseController {
    @GetMapping("/testcaseList")
    @ResponseBody
    public Response testcaseList(){
        try {
            Response response = new Response();
            response.putContext("testcaseList", this.ktsService.findAllTestcase());
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
            response.putContext("testcase", this.ktsService.findTestcase(testcaseGuid));
            return response;
        } catch (Exception e){
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
        }
    }
}
