package com.krafton.kts.frontend.cross.controller;

import com.krafton.kts.backend.cross.service.SaveActionService;
import com.krafton.kts.backend.domain.action.domain.command.SaveCurrentTestcaseActionsCommand;
import com.krafton.kts.frontend.common.ERROR_CODE;
import com.krafton.kts.frontend.common.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class SaveActionController {

    private final SaveActionService saveActionService;

    @PostMapping("/saveCurrentTestcaseActions")
    @ResponseBody
    public Response saveCurrentTestcaseActions(@RequestBody SaveCurrentTestcaseActionsCommand command){
        try {
            this.saveActionService.saveAction(command);
            return new Response();
        } catch(Exception e){
            return new Response(ERROR_CODE.ERR_COMMON, e.getMessage());
        }
    }
}
