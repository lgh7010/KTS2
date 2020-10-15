package com.krafton.kts.frontend.action.controller;

import com.krafton.kts.backend.action.domain.KTS_PROPERTY;
import com.krafton.kts.backend.action.domain.SavePropertiesCommand;
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
    public Response saveProperties(@RequestBody SavePropertiesCommand command){
        try {
            this.service_property.saveProperties(command);
            return new Response();
        } catch(Exception e){
            return new Response(ERROR_CODE.ERR_COMMON, e.getMessage());
        }
    }
}
