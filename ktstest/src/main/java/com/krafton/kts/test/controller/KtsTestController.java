package com.krafton.kts.test.controller;

import com.krafton.kts.common.Response;
import com.krafton.kts.test.services.KtsTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class KtsTestController {

    private final KtsTestService ktsService;

    @GetMapping("/findAllTest")
    @ResponseBody
    public Response findAllTest(){
        Response response = new Response();
        response.putContext("list", this.ktsService.findAllTest());
        return response;
    }

    @GetMapping("/findTest")
    @ResponseBody
    public Response findTest(@RequestParam String testGuid){
        Response response = new Response();
        response.putContext("test", this.ktsService.findTest(testGuid));
        return response;
    }

    @GetMapping("/removeTest")
    @ResponseBody
    public Response removeTest(){
        return null;
    }

    @GetMapping("/addTest")
    @ResponseBody
    public Response addTest(){
        return null;
    }
}
