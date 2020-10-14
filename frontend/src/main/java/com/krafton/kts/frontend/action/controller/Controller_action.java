package com.krafton.kts.frontend.action.controller;

import com.krafton.kts.backend.action.domain.KTS_ACTION;
import com.krafton.kts.backend.action.service.Service_action;
import com.krafton.kts.backend.test_rel_testcase.domain.TEST_REL_TESTCASE;
import com.krafton.kts.backend.testcase.domain.KTS_TESTCASE;
import com.krafton.kts.frontend.common.ERROR_CODE;
import com.krafton.kts.frontend.common.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
public class Controller_action {

    private final Service_action service_action;

    @Autowired
    public Controller_action(Service_action service_action){
        this.service_action = service_action;
    }

    @GetMapping("/actionDic")
    @ResponseBody
    public Response actionDic(
            HttpServletRequest req,
            @RequestParam(value = "TESTCASE_SEQ") int TESTCASE_SEQ,
            HttpServletResponse res
    ){
        try {
            List<KTS_ACTION> list = this.service_action.findActionsByTESTCASE_SEQ(TESTCASE_SEQ);
            Map<String, KTS_ACTION> ret = new HashMap<>();
            for (Iterator<KTS_ACTION> iter = list.iterator(); iter.hasNext();){
                KTS_ACTION tc = iter.next();
                ret.put(tc.getACTION_GUID(), tc);
            }

            Response response = new Response();
            response.putContext("actionDic", ret);
            return response;
        } catch(Exception e) {
            return new Response(ERROR_CODE.ERR_COMMON, e.getMessage());
        }
    }

    @GetMapping("/actionTempleteDic")
    @ResponseBody
    public Response actionTempleteDic(
        HttpServletRequest req,
        HttpServletResponse res
    ){
        try {
            Response response = new Response();
            response.putContext("actionTempleteDic", this.service_action.findAllTemplete());
            return response;
        } catch(Exception e){
            return new Response(ERROR_CODE.ERR_COMMON, e.getMessage());
        }
    }

    @PostMapping("/saveActionDic")
    @ResponseBody
    public Response saveActionDic(
            HttpServletRequest req,
            @RequestBody String requestJsonStr,
            HttpServletResponse res
    ){
        try {
            JSONObject requestJsonObj = new JSONObject(requestJsonStr);

            JSONObject actionDic = requestJsonObj.getJSONObject("ACTION_DIC");
            List<KTS_ACTION> list = new ArrayList<>();
            Iterator<String> iter = actionDic.keys();
            while(iter.hasNext()){
                JSONObject actionObject = actionDic.getJSONObject(iter.next());

                KTS_ACTION action = new KTS_ACTION();
                action.setACTION_GUID(actionObject.getString("action_GUID"));
                action.setTESTCASE_SEQ(actionObject.getInt("testcase_SEQ"));
                action.setIS_START(actionObject.getString("is_START"));
                action.setNEXT_ACTION_GUID(actionObject.getString("next_ACTION_GUID"));
                action.setACTION_ID(actionObject.getString("action_ID"));
                action.setX_POS(actionObject.getFloat("x_POS"));
                action.setY_POS(actionObject.getFloat("y_POS"));
                action.setDESCRIPTION(actionObject.getString("description"));
                action.setDELETED("N");

                list.add(action);
            }

            JSONArray removeActionSeqArray = requestJsonObj.getJSONArray("REMOVE_ACTION_GUID_LIST");
            List<String> removeList = new ArrayList<>();
            for(int i = 0; i < removeActionSeqArray.length(); i++){
                removeList.add(removeActionSeqArray.getString(i));
            }

            this.service_action.saveActionList(list, removeList);

            return new Response();
        } catch(Exception e){
            return new Response(ERROR_CODE.ERR_COMMON, e.getMessage());
        }
    }
}
