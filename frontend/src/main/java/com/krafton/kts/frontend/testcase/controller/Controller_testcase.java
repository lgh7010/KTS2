package com.krafton.kts.frontend.testcase.controller;

import com.krafton.kts.backend.testcase.domain.command.RemoveTestcaseCommand;
import com.krafton.kts.backend.testcase.service.TestcaseService;
import com.krafton.kts.frontend.common.ERROR_CODE;
import com.krafton.kts.frontend.common.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class Controller_testcase {

    private final TestcaseService service_testcase;

    @GetMapping("/testcaseList")
    @ResponseBody
    public Response testcaseList(){
        try {
            Response response = new Response();
            response.putContext("testcaseList", this.service_testcase.findAll());
            return response;
        } catch(Exception e){
            return new Response(ERROR_CODE.ERR_COMMON, e.getMessage());
        }
    }

    @PostMapping("/removeTestcase")
    @ResponseBody
    public Response removeTestcase(@RequestBody RemoveTestcaseCommand command){
        try {
            this.service_testcase.removeTestcase(command);
            return new Response();
        } catch(Exception e){
            return new Response(ERROR_CODE.ERR_COMMON, e.getMessage());
        }
    }

    @GetMapping("/testcase")
    @ResponseBody
    public Response testcase(@RequestParam(value = "testcaseGuid") String testcaseGuid){
        try {
            Response response = new Response();
            response.putContext("testcase", this.service_testcase.findTestcase(testcaseGuid));
            return response;
        } catch (Exception e){
            return new Response(ERROR_CODE.ERR_COMMON, e.getMessage());
        }
    }
}
