package com.krafton.kts.frontend.testcase.controller;

import com.krafton.kts.backend.testcase.service.Service_testcase;
import com.krafton.kts.frontend.common.ERROR_CODE;
import com.krafton.kts.frontend.common.Response;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class Controller_testcase {

    private final Service_testcase service_testcase;

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

    @PostMapping("/removeTestcase")
    @ResponseBody
    public Response removeTestcase(@RequestBody String requestJsonStr){
        try {
            JSONObject requestJsonObj = new JSONObject(requestJsonStr);
            this.service_testcase.removeTestcase(requestJsonObj.getString("testcaseGuid"));
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
