package com.krafton.kts.frontend.test_rel_testcase.controller;

import com.krafton.kts.backend.test_rel_testcase.domain.TEST_REL_TESTCASE;
import com.krafton.kts.backend.test_rel_testcase.service.Service_test_rel_testcase;
import com.krafton.kts.frontend.common.ERROR_CODE;
import com.krafton.kts.frontend.common.Response;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class Controller_test_rel_testcase {

    private final Service_test_rel_testcase service_test_rel_testcase;

    @GetMapping("/testRelTestcaseList")
    @ResponseBody
    public Response testRelTestcaseList(@RequestParam(value = "testSeq") int testSeq){
        try {
            Response response = new Response();
            response.putContext("testRelTestcaseList", this.service_test_rel_testcase.findTestRelTestcaseByTEST_SEQ(testSeq));
            return response;
        } catch(Exception e){
            return new Response(ERROR_CODE.ERR_COMMON, e.getMessage());
        }
    }

    @PostMapping("/testRelTestcaseSave")
    @ResponseBody
    public Response testRelTestcaseSave(@RequestBody String requestJsonStr){
        try {
            JSONObject requestJsonObj = new JSONObject(requestJsonStr);

            int testSeq = requestJsonObj.getInt("testSeq");
            String testName = requestJsonObj.getString("testName");
            String testDescription = requestJsonObj.getString("testDescription");

            JSONArray relArray = requestJsonObj.getJSONArray("relationList");
            List<TEST_REL_TESTCASE> list = new ArrayList<>();
            for(int listIndex = 0; listIndex < relArray.length(); listIndex++){
                JSONObject relObj = relArray.getJSONObject(listIndex);
                TEST_REL_TESTCASE elem = new TEST_REL_TESTCASE();
                int relSeq = relObj.getInt("relationSeq");
                elem.setRelationSeq(relSeq > 0 ? relSeq : 0);
                //elem.setTEST_SEQ(relObj.getInt("test_SEQ"));
                elem.setTestSeq(testSeq);
                elem.setListIndex(listIndex);
                elem.setTestcaseGuid(relObj.getString("testcaseGuid"));
                list.add(elem);
            }

            JSONArray removeRelationSeqArray = requestJsonObj.getJSONArray("removeRelationSeqList");
            List<Integer> removeRelationSeqList = new ArrayList<>();
            for(int i = 0; i < removeRelationSeqArray.length(); i++){
                removeRelationSeqList.add(removeRelationSeqArray.getInt(i));
            }
            this.service_test_rel_testcase.saveTestRelTestcase(list, testSeq, testName, testDescription, removeRelationSeqList);

            return new Response();
        } catch(Exception e){
            return new Response(ERROR_CODE.ERR_COMMON, e.getMessage());
        }
    }

    @GetMapping("/testRelTestcaseDerived")
    @ResponseBody
    public Response testRelTestcaseDerived(@RequestParam(value = "testSeq") int testSeq){
        try {
            Response response = new Response();
            response.putContext("list", this.service_test_rel_testcase.findTestRelTestcaseDerived(testSeq));
            return response;
        } catch(Exception e){
            return new Response(ERROR_CODE.ERR_COMMON, e.getMessage());
        }
    }
}
