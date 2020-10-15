package com.krafton.kts.frontend.test.controller;

import com.krafton.kts.backend.test.domain.command.RemoveTestCommand;
import com.krafton.kts.frontend.common.ERROR_CODE;
import com.krafton.kts.frontend.common.Response;
import com.krafton.kts.backend.test.service.Service_test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class Controller_test {

    private final Service_test testListService;

    @Autowired
    public Controller_test(Service_test testListService){
        this.testListService = testListService;
    }

    @GetMapping("/testList")
    @ResponseBody
    public Response testList(){
        Response response = new Response();
        response.putContext("list", this.testListService.findAll());
        return response;
    }

    @GetMapping("/test")
    @ResponseBody
    public Response test(@RequestParam int testSeq){
        Response response = new Response();
        response.putContext("test", this.testListService.find(testSeq));
        return response;
    }

    @PostMapping("/removeTest")
    @ResponseBody
    public Response removeTest(@RequestBody RemoveTestCommand command){
        try {
            this.testListService.removeTest(command);
            return new Response();
        } catch(Exception e){
            return new Response(ERROR_CODE.ERR_COMMON, e.getMessage());
        }
    }
}
