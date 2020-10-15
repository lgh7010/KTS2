package com.krafton.kts.frontend.test_rel_testcase.controller;

import com.krafton.kts.backend.test_rel_testcase.domain.TestRelTestcaseSaveCommand;
import com.krafton.kts.backend.test_rel_testcase.service.Service_test_rel_testcase;
import com.krafton.kts.frontend.common.ERROR_CODE;
import com.krafton.kts.frontend.common.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class Controller_test_rel_testcase {

    private final Service_test_rel_testcase service_test_rel_testcase;

    @PostMapping("/testRelTestcaseSave")
    @ResponseBody
    public Response testRelTestcaseSave(@RequestBody TestRelTestcaseSaveCommand command){
        try {
            this.service_test_rel_testcase.saveTestRelTestcase(command);
            return new Response();
        } catch(Exception e){
            return new Response(ERROR_CODE.ERR_COMMON, e.getMessage());
        }
    }

    @GetMapping("/testRelTestcaseDerived")
    @ResponseBody
    public Response testRelTestcaseDerived(@RequestParam(value = "testSeq") int testSeq){
        try {
            Response response = new Response();
            response.putContext("list", this.service_test_rel_testcase.findTestRelTestcaseDerived(testSeq));
            return response;
        } catch(Exception e){
            return new Response(ERROR_CODE.ERR_COMMON, e.getMessage());
        }
    }
}
