package com.krafton.kts.test_rel_testcase.controller;

import com.krafton.kts.common.ErrorCode;
import com.krafton.kts.common.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestRelTestcaseController {
    //test_rel_testcase
    @GetMapping("/testRelTestcaseJoinTestcase")
    @ResponseBody
    public Response testRelTestcaseJoinTestcase(@RequestParam(value = "testGuid") String testGuid){
        try {
            Response response = new Response();
            response.putContext("list", this.ktsService.findTestRelTestcaseJoinTestcase(testGuid));
            return response;
        } catch(Exception e){
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
        }
    }
}
