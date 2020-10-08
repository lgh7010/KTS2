package com.krafton.kts.frontend.test.controller;

import com.krafton.kts.frontend.common.ERROR_CODE;
import com.krafton.kts.frontend.common.Response;
import com.krafton.kts.backend.test.service.Service_test;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class Controller_test {

    private final Service_test testListService;

    @Autowired
    public Controller_test(Service_test testListService){
        this.testListService = testListService;
    }

    @GetMapping("/testList")
    @ResponseBody
    public Response testList(HttpServletRequest req, HttpServletResponse res){
        Response response = new Response();
        response.putContext("testList", this.testListService.findAll());
        return response;
    }

    @PostMapping("/removeTest")
    @ResponseBody
    public Response removeTest(HttpServletRequest req, @RequestBody String requestJsonStr, HttpServletResponse res){
        try {
            JSONObject requestJsonObj = new JSONObject(requestJsonStr);
            this.testListService.removeTest(requestJsonObj.getInt("TEST_SEQ"));
            return new Response();
        } catch(Exception e){
            return new Response(ERROR_CODE.ERR_COMMON, e.getMessage());
        }
    }
}
