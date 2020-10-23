package com.krafton.kts.running_test.controller;

import com.krafton.kts.common.ErrorCode;
import com.krafton.kts.common.Response;
import com.krafton.kts.running_test.services.KtsRunningTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class KtsRunningTestController {

    private final KtsRunningTestService ktsRunningTestService;

    @GetMapping("/findAllRunningTest")
    @ResponseBody
    public Response findAllRunningTest(){
        try {
            Response response = new Response();
            response.putContext("runningTests", this.ktsRunningTestService.findAllRunningTest());
            return response;
        } catch(Exception e){
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
        }
    }
}
