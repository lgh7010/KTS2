package com.krafton.kts.frontend.action.controller;

import com.krafton.kts.backend.action.domain.KTS_PROPERTY;
import com.krafton.kts.backend.action.service.Service_property;
import com.krafton.kts.frontend.common.ERROR_CODE;
import com.krafton.kts.frontend.common.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class Controller_property {
    private final Service_property service_property;

    @Autowired
    public Controller_property(Service_property service_property){
        this.service_property = service_property;
    }

    @GetMapping("/propertiesTemplate")
    @ResponseBody
    public Response propertiesTemplate(@RequestParam(value = "actionId") String actionId){
        try {
            Response response = new Response();
            response.putContext("list", this.service_property.findPropertyTemplate(actionId));
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
            response.putContext("list", this.service_property.findProperty(actionGuid));
            return response;
        } catch(Exception e) {
            return new Response(ERROR_CODE.ERR_COMMON, e.getMessage());
        }
    }

    @PostMapping("/saveProperties")
    @ResponseBody
    public Response saveProperties(@RequestBody String requestJsonStr){
        try {
            JSONObject requestJsonObj = new JSONObject(requestJsonStr);

            JSONArray array = requestJsonObj.getJSONArray("properties");
            List<KTS_PROPERTY> list = new ArrayList<>();
            for(int i = 0; i < array.length(); i++){
                JSONObject propObj = array.getJSONObject(i);

                KTS_PROPERTY prop = new KTS_PROPERTY();
                prop.setPropertySeq(propObj.getInt("propertySeq"));
                prop.setPropertyName(propObj.getString("propertyName"));
                prop.setPropertyValue(propObj.getString("propertyValue"));
                prop.setActionGuid(propObj.getString("actionGuid"));
                list.add(prop);
            }

            String actionGuid = requestJsonObj.getString("actionGuid");
            String actionId = requestJsonObj.getString("actionId");

            this.service_property.saveProperties(list, actionGuid, actionId);

            return new Response();
        } catch(Exception e){
            return new Response(ERROR_CODE.ERR_COMMON, e.getMessage());
        }
    }
}
