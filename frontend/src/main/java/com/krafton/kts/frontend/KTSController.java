package com.krafton.kts.frontend;

import com.krafton.kts.interfaces.repository.derived.OnFinishActionCommand;
import com.krafton.kts.interfaces.repository.derived.RunTestCommand;
import com.krafton.kts.interfaces.repository.derived.SaveTestcaseCommand;
import com.krafton.kts.interfaces.repository.test.RemoveTestCommand;
import com.krafton.kts.interfaces.repository.testcase.RemoveTestcaseCommand;
import com.krafton.kts.interfaces.repository.testreltestcase.SaveTestRelTestcaseCommand;
import com.krafton.kts.domains.entity.KtsAction;
import com.krafton.kts.frontend.common.ErrorCode;
import com.krafton.kts.frontend.common.Response;
import com.krafton.kts.interfaces.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class KTSController {

    private final FindActionPropertyServiceInterface findActionPropertyServiceInterface;
    private final FindActionServiceInterface findActionServiceInterface;
    private final FindPropertyServiceInterface findPropertyServiceInterface;
    private final FindTestcaseServiceInterface findTestcaseServiceInterface;
    private final RemoveTestcaseServiceInterface removeTestcaseServiceInterface;
    private final SaveTestcaseServiceInterface saveTestcaseServiceInterface;

    private final FindTestServiceInterface findTestServiceInterface;
    private final RemoveTestServiceInterface removeTestService;

    private final FindTestRelTestcaseJoinTestcaseInterface findTestRelTestcaseJoinTestcaseInterface;
    private final SaveTestRelTestcaseServiceInterface saveTestRelTestcaseService;

    private final FindRunningTestServiceInterface findRunningTestServiceInterface;

    private final TestManagingServiceInterface testManagingServiceInterface;

    //transactional
    @PostMapping("/saveCurrentTestcaseActions")
    @ResponseBody
    public Response saveCurrentTestcaseActions(@RequestBody SaveTestcaseCommand command){
        try {
            this.saveTestcaseServiceInterface.saveTestcase(command);
            return new Response();
        } catch(Exception e){
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
        }
    }
    @PostMapping("/removeTestcase")
    @ResponseBody
    public Response removeTestcase(@RequestBody RemoveTestcaseCommand command){
        try {
            this.removeTestcaseServiceInterface.removeTestcase(command);
            return new Response();
        } catch(Exception e){
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
        }
    }
    @PostMapping("/testRelTestcaseSave")
    @ResponseBody
    public Response testRelTestcaseSave(@RequestBody SaveTestRelTestcaseCommand command){
        try {
            this.saveTestRelTestcaseService.saveTestRelTestcase(command);
            return new Response();
        } catch(Exception e){
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
        }
    }
    @PostMapping("/removeTest")
    @ResponseBody
    public Response removeTest(@RequestBody RemoveTestCommand command){
        try {
            this.removeTestService.removeTest(command);
            return new Response();
        } catch(Exception e){
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
        }
    }
    @PostMapping("/runTest")
    @ResponseBody
    public Response runTest(@RequestBody RunTestCommand command){
        try {
            Response response = new Response();
            response.putContext("instruction", this.testManagingServiceInterface.runTest(command));
            return response;
        } catch (Exception e){
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
        }
    }
    @PostMapping("/onFinishAction")
    @ResponseBody
    public Response onFinishAction(@RequestBody OnFinishActionCommand command){
        try {
            Response response = new Response();
            response.putContext("instruction", this.testManagingServiceInterface.onFinishAction(command));
            return response;
        } catch(Exception e){
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
        }
    }

    //action
    @GetMapping("/currentTestcaseActions")
    @ResponseBody
    public Response currentTestcaseActions(@RequestParam(value = "testcaseGuid") String testcaseGuid){
        try {
            List<KtsAction> list = this.findActionServiceInterface.findAction(testcaseGuid);
            Map<String, KtsAction> ret = new HashMap<>();
            for (Iterator<KtsAction> iter = list.iterator(); iter.hasNext();){
                KtsAction tc = iter.next();
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
            response.putContext("actionTemplates", this.findActionServiceInterface.getActionTemplate());
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
            response.putContext("list", this.findPropertyServiceInterface.getPropertyTemplate(actionId));
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
            response.putContext("map", this.findActionPropertyServiceInterface.findProperties(testcaseGuid));
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
        response.putContext("list", this.findTestServiceInterface.findAllTest());
        return response;
    }

    @GetMapping("/findTest")
    @ResponseBody
    public Response findTest(@RequestParam String testGuid){
        Response response = new Response();
        response.putContext("test", this.findTestServiceInterface.findTest(testGuid));
        return response;
    }

    //test_rel_testcase
    @GetMapping("/testRelTestcaseJoinTestcase")
    @ResponseBody
    public Response testRelTestcaseJoinTestcase(@RequestParam(value = "testGuid") String testGuid){
        try {
            Response response = new Response();
            response.putContext("list", this.findTestRelTestcaseJoinTestcaseInterface.findTestRelTestcaseJoinTestcase(testGuid));
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
            response.putContext("testcaseList", this.findTestcaseServiceInterface.findAllTestcase());
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
            response.putContext("testcase", this.findTestcaseServiceInterface.findTestcase(testcaseGuid));
            return response;
        } catch (Exception e){
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
        }
    }

    //running_test
    @GetMapping("/findAllRunningTest")
    @ResponseBody
    public Response findAllRunningTest(){
        try {
            Response response = new Response();
            response.putContext("runningTests", this.findRunningTestServiceInterface.findAllRunningTest());
            return response;
        } catch(Exception e){
            return new Response(ErrorCode.ERR_COMMON, e.getMessage());
        }
    }
}
