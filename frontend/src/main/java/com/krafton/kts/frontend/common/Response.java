package com.krafton.kts.frontend.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.thymeleaf.util.MapUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public class Response implements Serializable {
    @JsonProperty(value="return_code")
    private int returnCode;

    @JsonProperty(value="return_message")
    private String returnMessage;

    @Setter(AccessLevel.NONE)
    private Map<String, Object> context;

    public Response(){
        context = new HashMap<>();
        setReturnCode(0);
        setReturnMessage("success");
    }
    public Response(int returnCode, String returnMessage){
        context = new HashMap<>();
        setReturnCode(returnCode);
        setReturnMessage(returnMessage);
    }

    @JsonProperty(value="context")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Map<String, Object> getContext(){
        if(MapUtils.isEmpty(context)){
            return null;
        }
        return context;
    }
    public Object putContext(String key, Object value){
        return context.put(key, value);
    }
}
