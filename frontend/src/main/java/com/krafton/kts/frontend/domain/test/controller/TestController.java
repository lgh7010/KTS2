package com.krafton.kts.frontend.domain.test.controller;

import com.krafton.kts.backend.domain.test.domain.command.RemoveTestCommand;
import com.krafton.kts.backend.domain.test.service.interfaces.TestInterfaceMybatis;
import com.krafton.kts.frontend.common.ErrorCode;
import com.krafton.kts.frontend.common.Response;
import com.krafton.kts.backend.domain.test.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;
    private final TestInterfaceMybatis testInterfaceMybatis;

    @GetMapping("/findAllTest")
    @ResponseBody
    public Response findAllTest(){
        System.out.println(testInterfaceMybatis.findAllTest());
        Response response = new Response();
        response.putContext("list", this.testService.findAllTest());
        return response;
    }

    @GetMapping("/findTest")
    @ResponseBody
    public Response findTest(@RequestParam int testSeq){
        Response response = new Response();
        response.putContext("test", this.testService.findTest(testSeq));
        return response;
    }

    @PostMapping("/removeTest")
    @ResponseBody
    public Response removeTest(@RequestBody RemoveTestCommand command){
        try {
            this.testService.removeTest(command);
            return new Response();
        } catch(Exception e){
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
        }
    }
}
