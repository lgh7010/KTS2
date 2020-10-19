package com.krafton.kts.frontend.domain.test_rel_testcase.controller;

import com.krafton.kts.backend.domain.test_rel_testcase.service.TestRelTestcaseService;
import com.krafton.kts.frontend.common.ErrorCode;
import com.krafton.kts.frontend.common.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class TestRelTestcaseController {

    private final TestRelTestcaseService service_test_rel_testcase;

    @GetMapping("/testRelTestcaseDerived")
    @ResponseBody
    public Response testRelTestcaseDerived(@RequestParam(value = "testGuid") String testGuid){
        try {
            Response response = new Response();
            response.putContext("list", this.service_test_rel_testcase.findTestRelTestcaseDerived(testGuid));
            return response;
        } catch(Exception e){
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
        }
    }
}
