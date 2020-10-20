package com.krafton.kts.frontend;

import com.krafton.kts.backend.domain.property.domain.command.SavePropertiesCommand;
import com.krafton.kts.backend.service.command.SaveTestcaseCommand;
import com.krafton.kts.backend.domain.action.domain.db.KTS_ACTION;
import com.krafton.kts.backend.domain.test.domain.command.RemoveTestCommand;
import com.krafton.kts.backend.domain.test_rel_testcase.domain.command.SaveTestRelTestcaseCommand;
import com.krafton.kts.backend.domain.testcase.domain.command.RemoveTestcaseCommand;
import com.krafton.kts.backend.service.MySystemService;
import com.krafton.kts.frontend.common.ErrorCode;
import com.krafton.kts.frontend.common.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MySystemController {

    private final MySystemService systemService;

    @PostMapping("/saveCurrentTestcaseActions")
    @ResponseBody
    public Response saveCurrentTestcaseActions(@RequestBody SaveTestcaseCommand command){
        try {
            this.systemService.saveTestcase(command);
            return new Response();
        } catch(Exception e){
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
        }
    }

    @PostMapping("/removeTestcase")
    @ResponseBody
    public Response removeTestcase(@RequestBody RemoveTestcaseCommand command){
        try {
            this.systemService.removeTestcase(command);
            return new Response();
        } catch(Exception e){
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
        }
    }

    @PostMapping("/testRelTestcaseSave")
    @ResponseBody
    public Response testRelTestcaseSave(@RequestBody SaveTestRelTestcaseCommand command){
        try {
            this.systemService.saveTestRelTestcase(command);
            return new Response();
        } catch(Exception e){
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
        }
    }

    @PostMapping("/removeTest")
    @ResponseBody
    public Response removeTest(@RequestBody RemoveTestCommand command){
        try {
            this.systemService.removeTest(command);
            return new Response();
        } catch(Exception e){
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
        }
    }

    //action
    @GetMapping("/currentTestcaseActions")
    @ResponseBody
    public Response currentTestcaseActions(@RequestParam(value = "testcaseGuid") String testcaseGuid){
        try {
            List<KTS_ACTION> list = this.systemService.findAction(testcaseGuid);
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
            response.putContext("actionTemplates", this.systemService.getActionTemplate());
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
            response.putContext("list", this.systemService.getPropertyTemplate(actionId));
            return response;
        } catch(Exception e) {
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
        }
    }
    @GetMapping("/findProperties")
    @ResponseBody
    public Response findProperties(@RequestParam(value = "testcaseGuid") String testcaseGuid){
        try {
            Response response = new Response();
            response.putContext("map", this.systemService.findProperties(testcaseGuid));
            return response;
        } catch(Exception e){
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
        }
    }

    //test
    @GetMapping("/findAllTest")
    @ResponseBody
    public Response findAllTest(){
        Response response = new Response();
        response.putContext("list", this.systemService.findAllTest());
        return response;
    }

    @GetMapping("/findTest")
    @ResponseBody
    public Response findTest(@RequestParam String testGuid){
        Response response = new Response();
        response.putContext("test", this.systemService.findTest(testGuid));
        return response;
    }

    //test_rel_testcase
    @GetMapping("/testRelTestcaseDerived")
    @ResponseBody
    public Response testRelTestcaseDerived(@RequestParam(value = "testGuid") String testGuid){
        try {
            Response response = new Response();
            response.putContext("list", this.systemService.findTestRelTestcaseDerived(testGuid));
            return response;
        } catch(Exception e){
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
        }
    }

    //testcase
    @GetMapping("/testcaseList")
    @ResponseBody
    public Response testcaseList(){
        try {
            Response response = new Response();
            response.putContext("testcaseList", this.systemService.findAll());
            return response;
        } catch(Exception e){
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
        }
    }

    @GetMapping("/testcase")
    @ResponseBody
    public Response testcase(@RequestParam(value = "testcaseGuid") String testcaseGuid){
        try {
            Response response = new Response();
            response.putContext("testcase", this.systemService.findTestcase(testcaseGuid));
            return response;
        } catch (Exception e){
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
        }
    }
}
