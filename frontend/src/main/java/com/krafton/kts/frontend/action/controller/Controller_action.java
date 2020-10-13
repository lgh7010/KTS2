package com.krafton.kts.frontend.action.controller;

import com.krafton.kts.backend.action.domain.KTS_ACTION;
import com.krafton.kts.backend.action.service.Service_action;
import com.krafton.kts.backend.testcase.domain.KTS_TESTCASE;
import com.krafton.kts.frontend.common.ERROR_CODE;
import com.krafton.kts.frontend.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
            Map<Integer, KTS_ACTION> ret = new HashMap<>();
            for (Iterator<KTS_ACTION> iter = list.iterator(); iter.hasNext();){
                KTS_ACTION tc = iter.next();
                ret.put(tc.getACTION_SEQ(), tc);
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
}
