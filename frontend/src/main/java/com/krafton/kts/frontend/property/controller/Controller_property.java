package com.krafton.kts.frontend.property.controller;

import com.krafton.kts.backend.action.domain.KTS_ACTION;
import com.krafton.kts.backend.property.domain.KTS_PROPERTY;
import com.krafton.kts.backend.property.domain.KTS_PROPERTY_TEMPLETE;
import com.krafton.kts.backend.property.service.Service_property;
import com.krafton.kts.backend.test_rel_testcase.domain.TEST_REL_TESTCASE;
import com.krafton.kts.frontend.common.ERROR_CODE;
import com.krafton.kts.frontend.common.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
public class Controller_property {
    private final Service_property service_property;

    @Autowired
    public Controller_property(Service_property service_property){
        this.service_property = service_property;
    }

    @GetMapping("/propertiesTemplete")
    @ResponseBody
    public Response propertiesTemplete(
            HttpServletRequest req,
            @RequestParam(value = "ACTION_ID") String ACTION_ID,
            HttpServletResponse res
    ){
        try {
            List<KTS_PROPERTY_TEMPLETE> list = this.service_property.findPropertyTemplete(ACTION_ID);

            Response response = new Response();
            response.putContext("templeteProperties", list);
            return response;
        } catch(Exception e) {
            return new Response(ERROR_CODE.ERR_COMMON, e.getMessage());
        }
    }

    @GetMapping("/properties")
    @ResponseBody
    public Response properties(
            HttpServletRequest req,
            @RequestParam(value = "ACTION_GUID") String ACTION_GUID,
            HttpServletResponse res
    ){
        try {
            List<KTS_PROPERTY> list = this.service_property.findProperty(ACTION_GUID);

            Response response = new Response();
            response.putContext("properties", list);
            return response;
        } catch(Exception e) {
            return new Response(ERROR_CODE.ERR_COMMON, e.getMessage());
        }
    }

    @PostMapping("/saveProperties")
    @ResponseBody
    public Response saveProperties(
            HttpServletRequest req,
            @RequestBody String requestJsonStr,
            HttpServletResponse res
    ){
        try {
            JSONObject requestJsonObj = new JSONObject(requestJsonStr);

            JSONArray array = requestJsonObj.getJSONArray("PROPERTIES");
            List<KTS_PROPERTY> list = new ArrayList<>();
            for(int i = 0; i < array.length(); i++){
                JSONObject propObj = array.getJSONObject(i);

                KTS_PROPERTY prop = new KTS_PROPERTY();
                prop.setPROPERTY_SEQ(propObj.getInt("property_SEQ"));
                prop.setPROPERTY_NAME(propObj.getString("property_NAME"));
                prop.setPROPERTY_VALUE(propObj.getString("property_VALUE"));
                prop.setACTION_GUID(propObj.getString("action_GUID"));
                list.add(prop);
            }

            String ACTION_GUID = requestJsonObj.getString("ACTION_GUID");
            String ACTION_ID = requestJsonObj.getString("ACTION_ID");

            this.service_property.saveProperties(list, ACTION_GUID, ACTION_ID);

            return new Response();
        } catch(Exception e){
            return new Response(ERROR_CODE.ERR_COMMON, e.getMessage());
        }
    }
}
