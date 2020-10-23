package com.krafton.kts.transactional.controller;

import com.krafton.kts.common.ErrorCode;
import com.krafton.kts.common.Response;
import com.krafton.kts.crossdomain.command.OnFinishActionCommand;
import com.krafton.kts.crossdomain.command.RunTestCommand;
import com.krafton.kts.crossdomain.command.SaveTestcaseCommand;
import com.krafton.kts.domain.test.command.RemoveTestCommand;
import com.krafton.kts.domain.test_rel_testcase.command.SaveTestRelTestcaseCommand;
import com.krafton.kts.domain.testcase.command.RemoveTestcaseCommand;
import com.krafton.kts.transactional.services.KtsTransactionalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class KtsTransactionalController {

    private final KtsTransactionalService ktsTransactionalService;

    @PostMapping("/saveCurrentTestcaseActions")
    @ResponseBody
    public Response saveCurrentTestcaseActions(@RequestBody SaveTestcaseCommand command){
        try {
            this.ktsTransactionalService.saveTestcase(command);
            return new Response();
        } catch(Exception e){
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
        }
    }
    @PostMapping("/removeTestcase")
    @ResponseBody
    public Response removeTestcase(@RequestBody RemoveTestcaseCommand command){
        try {
            this.ktsTransactionalService.removeTestcase(command);
            return new Response();
        } catch(Exception e){
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
        }
    }
    @PostMapping("/testRelTestcaseSave")
    @ResponseBody
    public Response testRelTestcaseSave(@RequestBody SaveTestRelTestcaseCommand command){
        try {
            this.ktsTransactionalService.saveTestRelTestcase(command);
            return new Response();
        } catch(Exception e){
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
        }
    }
    @PostMapping("/removeTest")
    @ResponseBody
    public Response removeTest(@RequestBody RemoveTestCommand command){
        try {
            this.ktsTransactionalService.removeTest(command);
            return new Response();
        } catch(Exception e){
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
        }
    }
    @PostMapping("/runTest")
    @ResponseBody
    public Response runTest(@RequestBody RunTestCommand command){
        try {
            Response response = new Response();
            response.putContext("instruction", this.ktsTransactionalService.runTest(command));
            return response;
        } catch (Exception e){
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
        }
    }
    @PostMapping("/onFinishAction")
    @ResponseBody
    public Response onFinishAction(@RequestBody OnFinishActionCommand command){
        try {
            Response response = new Response();
            response.putContext("instruction", this.ktsTransactionalService.onFinishAction(command));
            return response;
        } catch(Exception e){
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
        }
    }
    @GetMapping("/findProperties")
    @ResponseBody
    public Response findProperties(@RequestParam(value = "testcaseGuid") String testcaseGuid){
        try {
            Response response = new Response();
            response.putContext("map", this.ktsTransactionalService.findProperties(testcaseGuid));
            return response;
        } catch(Exception e){
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
        }
    }
}
