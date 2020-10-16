package com.krafton.kts.frontend.action.controller;

import com.krafton.kts.backend.action.domain.command.SavePropertiesCommand;
import com.krafton.kts.backend.action.domain.db.KTS_ACTION;
import com.krafton.kts.backend.action.domain.command.SaveCurrentTestcaseActionsCommand;
import com.krafton.kts.backend.action.service.ActionService;
import com.krafton.kts.backend.testcase.service.TestcaseService;
import com.krafton.kts.frontend.common.ERROR_CODE;
import com.krafton.kts.frontend.common.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class Controller_action {

    private final ActionService actionService;
    private final TestcaseService testcaseService;

    @GetMapping("/currentTestcaseActions")
    @ResponseBody
    public Response currentTestcaseActions(@RequestParam(value = "testcaseGuid") String testcaseGuid){
        try {
            List<KTS_ACTION> list = this.actionService.findAction(testcaseGuid);
            Map<String, KTS_ACTION> ret = new HashMap<>();
            for (Iterator<KTS_ACTION> iter = list.iterator(); iter.hasNext();){
                KTS_ACTION tc = iter.next();
                ret.put(tc.getActionGuid(), tc);
            }

            Response response = new Response();
            response.putContext("currentTestcaseActions", ret);
            return response;
        } catch(Exception e) {
            return new Response(ERROR_CODE.ERR_COMMON, e.getMessage());
        }
    }

    @GetMapping("/actionTemplates")
    @ResponseBody
    public Response actionTemplates(){
        try {
            Response response = new Response();
            response.putContext("actionTemplates", this.actionService.getActionTemplate());
            return response;
        } catch(Exception e){
            return new Response(ERROR_CODE.ERR_COMMON, e.getMessage());
        }
    }

    @PostMapping("/saveCurrentTestcaseActions")
    @ResponseBody
    public Response saveCurrentTestcaseActions(@RequestBody SaveCurrentTestcaseActionsCommand command){
        try {

            this.actionService.saveAction(command.getCurrentTestcaseActions(), command.getRemoveActionGuidList());
            this.testcaseService.addTestcase(command.getTestcase());

            return new Response();
        } catch(Exception e){
            return new Response(ERROR_CODE.ERR_COMMON, e.getMessage());
        }
    }





    @GetMapping("/propertiesTemplate")
    @ResponseBody
    public Response propertiesTemplate(@RequestParam(value = "actionId") String actionId){
        try {
            Response response = new Response();
            response.putContext("list", this.actionService.getPropertyTemplate(actionId));
            return response;
        } catch(Exception e) {
            return new Response(ERROR_CODE.ERR_COMMON, e.getMessage());
        }
    }

    @GetMapping("/properties")
    @ResponseBody
    public Response properties(@RequestParam(value = "actionGuid") String actionGuid){
        try {
            Response response = new Response();
            response.putContext("list", this.actionService.findProperty(actionGuid));
            return response;
        } catch(Exception e) {
            return new Response(ERROR_CODE.ERR_COMMON, e.getMessage());
        }
    }

    @PostMapping("/saveProperties")
    @ResponseBody
    public Response saveProperties(@RequestBody SavePropertiesCommand command){
        try {
            this.actionService.saveProperties(command);
            return new Response();
        } catch(Exception e){
            return new Response(ERROR_CODE.ERR_COMMON, e.getMessage());
        }
    }
}
