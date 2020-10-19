package com.krafton.kts.frontend.cross.controller;

import com.krafton.kts.backend.cross.service.CrossDomainInterface;
import com.krafton.kts.backend.domain.action.domain.command.SaveTestcaseCommand;
import com.krafton.kts.frontend.common.ErrorCode;
import com.krafton.kts.frontend.common.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class CrossDomainController {

    private final CrossDomainInterface crossDomainInterface;

    @PostMapping("/saveCurrentTestcaseActions")
    @ResponseBody
    public Response saveCurrentTestcaseActions(@RequestBody SaveTestcaseCommand command){
        try {
            this.crossDomainInterface.saveTestcase(command);
            return new Response();
        } catch(Exception e){
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
        }
    }
}
