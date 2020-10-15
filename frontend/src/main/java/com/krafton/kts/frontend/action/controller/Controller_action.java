package com.krafton.kts.frontend.action.controller;

import com.krafton.kts.backend.action.domain.KTS_ACTION;
import com.krafton.kts.backend.action.domain.SaveCurrentTestcaseActionsCommand;
import com.krafton.kts.backend.action.service.Service_action;
import com.krafton.kts.backend.testcase.domain.KTS_TESTCASE;
import com.krafton.kts.backend.testcase.service.Service_testcase;
import com.krafton.kts.frontend.common.ERROR_CODE;
import com.krafton.kts.frontend.common.Response;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class Controller_action {

    private final Service_action service_action;
    private final Service_testcase service_testcase;

    @GetMapping("/currentTestcaseActions")
    @ResponseBody
    public Response currentTestcaseActions(@RequestParam(value = "testcaseGuid") String testcaseGuid){
        try {
            List<KTS_ACTION> list = this.service_action.findActionsByTESTCASE_GUID(testcaseGuid);
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
            response.putContext("actionTemplates", this.service_action.findAllTemplate());
            return response;
        } catch(Exception e){
            return new Response(ERROR_CODE.ERR_COMMON, e.getMessage());
        }
    }

    @PostMapping("/saveCurrentTestcaseActions")
    @ResponseBody
    public Response saveCurrentTestcaseActions(@RequestBody SaveCurrentTestcaseActionsCommand command){
        try {

            this.service_action.saveActionList(command.getCurrentTestcaseActions(), command.getRemoveActionGuidList());
            this.service_testcase.addTestcase(command.getTestcase());

            return new Response();
        } catch(Exception e){
            return new Response(ERROR_CODE.ERR_COMMON, e.getMessage());
        }
    }
}
