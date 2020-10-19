package com.krafton.kts.frontend.cross.controller;

import com.krafton.kts.backend.domain.action.domain.command.SaveTestcaseCommand;
import com.krafton.kts.backend.domain.test.domain.command.RemoveTestCommand;
import com.krafton.kts.backend.domain.test_rel_testcase.domain.command.SaveTestRelTestcaseCommand;
import com.krafton.kts.backend.domain.testcase.domain.command.RemoveTestcaseCommand;
import com.krafton.kts.backend.cross.CrossService;
import com.krafton.kts.frontend.common.ErrorCode;
import com.krafton.kts.frontend.common.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class CrossController {

    private final CrossService crossService;

    @PostMapping("/saveCurrentTestcaseActions")
    @ResponseBody
    public Response saveCurrentTestcaseActions(@RequestBody SaveTestcaseCommand command){
        try {
            this.crossService.saveTestcase(command);
            return new Response();
        } catch(Exception e){
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
        }
    }

    @PostMapping("/removeTestcase")
    @ResponseBody
    public Response removeTestcase(@RequestBody RemoveTestcaseCommand command){
        try {
            this.crossService.removeTestcase(command);
            return new Response();
        } catch(Exception e){
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
        }
    }

    @PostMapping("/testRelTestcaseSave")
    @ResponseBody
    public Response testRelTestcaseSave(@RequestBody SaveTestRelTestcaseCommand command){
        try {
            this.crossService.saveTestRelTestcase(command);
            return new Response();
        } catch(Exception e){
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
        }
    }

    @PostMapping("/removeTest")
    @ResponseBody
    public Response removeTest(@RequestBody RemoveTestCommand command){
        try {
            this.crossService.removeTest(command);
            return new Response();
        } catch(Exception e){
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
        }
    }
}
