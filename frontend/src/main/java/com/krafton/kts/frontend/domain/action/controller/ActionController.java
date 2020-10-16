package com.krafton.kts.frontend.domain.action.controller;

import com.krafton.kts.backend.domain.action.domain.command.SavePropertiesCommand;
import com.krafton.kts.backend.domain.action.domain.db.KTS_ACTION;
import com.krafton.kts.backend.domain.action.service.ActionService;
import com.krafton.kts.frontend.common.ErrorCode;
import com.krafton.kts.frontend.common.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class ActionController {

    private final ActionService actionService;

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
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
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
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
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
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
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
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
        }
    }

    @PostMapping("/saveProperties")
    @ResponseBody
    public Response saveProperties(@RequestBody SavePropertiesCommand command){
        try {
            this.actionService.saveProperties(command);
            return new Response();
        } catch(Exception e){
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
        }
    }
}
