package com.krafton.kts.frontend.action.controller;

import com.krafton.kts.backend.action.domain.KTS_ACTION;
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

    @GetMapping("/actionDic")
    @ResponseBody
    public Response actionDic(@RequestParam(value = "testcaseGuid") String testcaseGuid){
        try {
            List<KTS_ACTION> list = this.service_action.findActionsByTESTCASE_GUID(testcaseGuid);
            Map<String, KTS_ACTION> ret = new HashMap<>();
            for (Iterator<KTS_ACTION> iter = list.iterator(); iter.hasNext();){
                KTS_ACTION tc = iter.next();
                ret.put(tc.getActionGuid(), tc);
            }

            Response response = new Response();
            response.putContext("actionDic", ret);
            return response;
        } catch(Exception e) {
            return new Response(ERROR_CODE.ERR_COMMON, e.getMessage());
        }
    }

    @GetMapping("/actionTemplateDic")
    @ResponseBody
    public Response actionTemplateDic(){
        try {
            Response response = new Response();
            response.putContext("actionTemplateDic", this.service_action.findAllTemplate());
            return response;
        } catch(Exception e){
            return new Response(ERROR_CODE.ERR_COMMON, e.getMessage());
        }
    }

    @PostMapping("/saveActionDic")
    @ResponseBody
    public Response saveActionDic(@RequestBody String requestJsonStr){
        try {
            JSONObject requestJsonObj = new JSONObject(requestJsonStr);

            JSONObject actionDic = requestJsonObj.getJSONObject("actionDic");
            List<KTS_ACTION> list = new ArrayList<>();
            Iterator<String> iter = actionDic.keys();
            while(iter.hasNext()){
                JSONObject actionObject = actionDic.getJSONObject(iter.next());

                KTS_ACTION action = new KTS_ACTION();
                action.setActionGuid(actionObject.getString("actionGuid"));
                action.setTestcaseGuid(actionObject.getString("testcaseGuid"));
                action.setIsStart(actionObject.getString("isStart"));
                action.setNextActionGuid(actionObject.getString("nextActionGuid"));
                action.setActionId(actionObject.getString("actionId"));
                action.setX(actionObject.getFloat("x"));
                action.setY(actionObject.getFloat("y"));
                action.setDescription(actionObject.getString("description"));
                action.setDeleted("N");

                list.add(action);
            }

            JSONArray removeActionSeqArray = requestJsonObj.getJSONArray("removeActionGuidList");
            List<String> removeList = new ArrayList<>();
            for(int i = 0; i < removeActionSeqArray.length(); i++){
                removeList.add(removeActionSeqArray.getString(i));
            }

            JSONObject testcaseObj = requestJsonObj.getJSONObject("testcase");
            KTS_TESTCASE tc = new KTS_TESTCASE();
            tc.setTestcaseGuid(testcaseObj.getString("testcaseGuid"));
            tc.setName(testcaseObj.getString("name"));
            tc.setDescription(testcaseObj.getString("description"));
            tc.setDeleted("N");

            this.service_action.saveActionList(list, removeList);
            this.service_testcase.addTestcase(tc);

            return new Response();
        } catch(Exception e){
            return new Response(ERROR_CODE.ERR_COMMON, e.getMessage());
        }
    }
}
