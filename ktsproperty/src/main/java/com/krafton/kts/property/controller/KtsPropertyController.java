package com.krafton.kts.property.controller;

import com.krafton.kts.common.ErrorCode;
import com.krafton.kts.common.Response;
import com.krafton.kts.property.services.KtsPropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class KtsPropertyController {

    private final KtsPropertyService ktsPropertyService;

    @GetMapping("/propertiesTemplate")
    @ResponseBody
    public Response propertiesTemplate(@RequestParam(value = "actionId") String actionId){
        try {
            Response response = new Response();
            response.putContext("list", this.ktsPropertyService.getPropertyTemplate(actionId));
            return response;
        } catch(Exception e) {
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
        }
    }
}
