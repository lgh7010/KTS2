package com.krafton.kts.frontend.test_rel_testcase.controller;

import com.krafton.kts.backend.test_rel_testcase.domain.TEST_REL_TESTCASE;
import com.krafton.kts.backend.test_rel_testcase.service.Service_test_rel_testcase;
import com.krafton.kts.frontend.common.ERROR_CODE;
import com.krafton.kts.frontend.common.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Controller_test_rel_testcase {

    private final Service_test_rel_testcase service_test_rel_testcase;

    @Autowired
    public Controller_test_rel_testcase(Service_test_rel_testcase service_test_rel_testcase) {
        this.service_test_rel_testcase = service_test_rel_testcase;
    }

    @GetMapping("/testRelTestcaseList")
    @ResponseBody
    public Response testRelTestcaseList(
            HttpServletRequest req,
            @RequestParam(value = "TEST_SEQ") int TEST_SEQ,
            HttpServletResponse res
    ){
        try {
            Response response = new Response();
            response.putContext("testRelTestcaseList", this.service_test_rel_testcase.findTestRelTestcaseByTEST_SEQ(TEST_SEQ));
            return response;
        } catch(Exception e){
            return new Response(ERROR_CODE.ERR_COMMON, e.getMessage());
        }
    }

    @PostMapping("/testRelTestcaseSave")
    @ResponseBody
    public Response testRelTestcaseSave(
            HttpServletRequest req,
            @RequestBody String requestJsonStr,
            HttpServletResponse res
    ){
        try {
            JSONObject requestJsonObj = new JSONObject(requestJsonStr);

            int testSeq = requestJsonObj.getInt("TEST_SEQ");
            String testName = requestJsonObj.getString("TEST_NAME");
            String testDescription = requestJsonObj.getString("TEST_DESCRIPTION");

            JSONArray relArray = requestJsonObj.getJSONArray("REL_LIST");
            List<TEST_REL_TESTCASE> list = new ArrayList<>();
            for(int listIndex = 0; listIndex < relArray.length(); listIndex++){
                JSONObject relObj = relArray.getJSONObject(listIndex);
                TEST_REL_TESTCASE elem = new TEST_REL_TESTCASE();
                int relSeq = relObj.getInt("relation_SEQ");
                elem.setRELATION_SEQ(relSeq > 0 ? relSeq : 0);
                //elem.setTEST_SEQ(relObj.getInt("test_SEQ"));
                elem.setTEST_SEQ(testSeq);
                elem.setLIST_INDEX(listIndex);
                elem.setTESTCASE_SEQ(relObj.getInt("testcase_SEQ"));
                list.add(elem);
            }

            JSONArray removeTestcaseSeqListArray = requestJsonObj.getJSONArray("REMOVE_TESTCASE_SEQ_LIST");
            List<Integer> removeTestcaseSeqList = new ArrayList<>();
            for(int i = 0; i < removeTestcaseSeqListArray.length(); i++){
                removeTestcaseSeqList.add(removeTestcaseSeqListArray.getInt(i));
            }
            this.service_test_rel_testcase.saveTestRelTestcase(list, testSeq, testName, testDescription, removeTestcaseSeqList);

            return new Response();
        } catch(Exception e){
            return new Response(ERROR_CODE.ERR_COMMON, e.getMessage());
        }
    }
}
