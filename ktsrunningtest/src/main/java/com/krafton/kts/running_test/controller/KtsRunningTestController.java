package com.krafton.kts.running_test.controller;

import com.krafton.kts.common.ErrorCode;
import com.krafton.kts.common.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class KtsRunningTestController {
    @GetMapping("/findAllRunningTest")
    @ResponseBody
    public Response findAllRunningTest(){
        try {
            Response response = new Response();
            response.putContext("runningTests", this.ktsService.findAllRunningTest());
            return response;
        } catch(Exception e){
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
        }
    }
}
