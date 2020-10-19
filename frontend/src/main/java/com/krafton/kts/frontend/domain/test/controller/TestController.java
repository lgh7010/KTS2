package com.krafton.kts.frontend.domain.test.controller;

import com.krafton.kts.frontend.common.Response;
import com.krafton.kts.backend.domain.test.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/findAllTest")
    @ResponseBody
    public Response findAllTest(){
        Response response = new Response();
        response.putContext("list", this.testService.findAllTest());
        return response;
    }

    @GetMapping("/findTest")
    @ResponseBody
    public Response findTest(@RequestParam String testGuid){
        Response response = new Response();
        response.putContext("test", this.testService.findTest(testGuid));
        return response;
    }
}
