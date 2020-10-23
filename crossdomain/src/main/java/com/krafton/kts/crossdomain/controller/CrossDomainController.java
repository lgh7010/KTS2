package com.krafton.kts.crossdomain.controller;

import com.krafton.kts.common.ErrorCode;
import com.krafton.kts.common.Response;
import com.krafton.kts.crossdomain.service.CrossDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class CrossDomainController {

    private final CrossDomainService crossDomainService;

    @GetMapping("/testRelTestcaseJoinTestcase")
    @ResponseBody
    public Response testRelTestcaseJoinTestcase(@RequestParam(value = "testGuid") String testGuid){
        try {
            Response response = new Response();
            response.putContext("list", this.crossDomainService.findTestRelTestcaseJoinTestcase(testGuid));
            return response;
        } catch(Exception e){
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
        }
    }
}
