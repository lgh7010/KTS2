package com.krafton.kts.frontend.test.controller;

import com.krafton.kts.backend.test.domain.command.RemoveTestCommand;
import com.krafton.kts.frontend.common.ERROR_CODE;
import com.krafton.kts.frontend.common.Response;
import com.krafton.kts.backend.test.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class Controller_test {

    private final TestService testListService;

    @GetMapping("/findAllTest")
    @ResponseBody
    public Response findAllTest(){
        Response response = new Response();
        response.putContext("list", this.testListService.findAllTest());
        return response;
    }

    @GetMapping("/findTest")
    @ResponseBody
    public Response findTest(@RequestParam int testSeq){
        Response response = new Response();
        response.putContext("test", this.testListService.findTest(testSeq));
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
