package com.krafton.kts;

import com.krafton.kts.domains.derived.TestRelTestcaseJoinTestcase;
import com.krafton.kts.domains.entity.*;
import com.krafton.kts.interfaces.repository.derived.NextTestInstructionResponse;
import com.krafton.kts.interfaces.repository.derived.OnFinishActionCommand;
import com.krafton.kts.interfaces.repository.derived.RunTestCommand;
import com.krafton.kts.interfaces.repository.derived.SaveTestcaseCommand;
import com.krafton.kts.interfaces.repository.test.RemoveTestCommand;
import com.krafton.kts.interfaces.repository.testreltestcase.SaveTestRelTestcaseCommand;
import com.krafton.kts.interfaces.service.FindTestRelTestcaseJoinTestcaseInterface;
import com.krafton.kts.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class KtsSystemIntegratedTest {

    private final String TESTCASE_GUID = "testcaseGuid1";
    private final String TESTCASE_NAME = "myTestcaseName";
    private final String TESTCASE_DESCRIPTION = "myTestcaseDescription";
    private final String TEST_GUID = "testGuid1";
    private final String TEST_NAME = "myTestName";
    private final String TEST_DESCRIPTION = "myTestDescription";
    private final String ACTION_ID = "actionId1";
    private final String ACTION_GUID = "actionGuid1";
    private final String RELATION_GUID = "relationGuid1";
    private final String RUNNING_TEST_GUID = "runningTestGuid1";
    private final String RUNNING_ACTION_GUID = "runningActionGuid1";
    private final String RUNNING_PROPERTY_GUID = "runningPropertyGuid1";
    private final String RUNNING_TESTCASE_GUID = "runningTestcaseGuid1";

    KtsTest test = new KtsTest();
    KtsTestcase testcase = new KtsTestcase();
    TestRelTestcase testRelTestcase = new TestRelTestcase();

    @BeforeEach
    public void beforeEach(){
        test.setTestGuid(TEST_GUID);
        test.setName(TEST_NAME);
        test.setDescription(TEST_DESCRIPTION);
        test.setDeleted("N");

        testcase.setTestcaseGuid(TESTCASE_GUID);
        testcase.setName(TESTCASE_NAME);
        testcase.setDescription(TESTCASE_DESCRIPTION);
        testcase.setDeleted("N");

        testRelTestcase.setRelationGuid(RELATION_GUID);
        testRelTestcase.setTestcaseGuid(testcase.getTestcaseGuid());
        testRelTestcase.setListIndex(0);
        testRelTestcase.setTestGuid(test.getTestGuid());
        testRelTestcase.setDeleted("N");





        SaveTestcaseCommand saveTestcaseCommand = new SaveTestcaseCommand();
        saveTestcaseCommand.setTestcase(testcase);
        this.saveTestcaseService.saveTestcase(saveTestcaseCommand);

        List<TestRelTestcase> relationList = new ArrayList<>();
        relationList.add(testRelTestcase);
        SaveTestRelTestcaseCommand saveTestRelTestcaseCommand = new SaveTestRelTestcaseCommand();
        saveTestRelTestcaseCommand.setRelationList(relationList);
        saveTestRelTestcaseCommand.setTestName(test.getName());
        saveTestRelTestcaseCommand.setTestDescription(test.getDescription());
        saveTestRelTestcaseCommand.setTestGuid(test.getTestGuid());
        this.saveTestRelTestcaseService.saveTestRelTestcase(saveTestRelTestcaseCommand);
    }

    //region service-dashboard
    @Autowired
    private FindRunningTestService findRunningTestService;
    @Test
    public void findAllRunningTest() {
        RunTestCommand runTestCommand = new RunTestCommand();
        runTestCommand.setTestGuid(TEST_GUID);
        this.testManagingService.runTest(runTestCommand);

        List<RunningTest> runningTestList = this.findRunningTestService.findAllRunningTest();
        RunningTest runningTest = null;
        assertTrue(runningTestList.stream().count() > 0);
        for(int i = 0; i < runningTestList.stream().count(); i++){
            if(runningTestList.get(i).getTestGuid().equals(TEST_GUID)){
                runningTest = runningTestList.get(i);
                break;
            }
        }
        assertFalse(runningTest == null);
        assertEquals(TEST_NAME, runningTest.getName());
        assertEquals(TEST_DESCRIPTION, runningTest.getDescription());
    }

    @Autowired
    private TestManagingService testManagingService;
    @Test
    public void runTest(){
        findAllRunningTest();
    }
    @Test
    public void onActionFinished(){
        RunTestCommand runTestCommand = new RunTestCommand();
        runTestCommand.setTestGuid(TEST_GUID);
        NextTestInstructionResponse response = this.testManagingService.runTest(runTestCommand);

        OnFinishActionCommand command = new OnFinishActionCommand();
        command.setRunningTestGuid(response.getRunningTest().getRunningTestGuid());
        command.setStatus("Success");
        response = this.testManagingService.onFinishAction(command);
        assertEquals(response.getIsFinished(), "Y");
    }
    //endregion

    //region service-test
    @Autowired
    private FindTestRelTestcaseService findTestRelTestcaseService;
    @Test
    public void findTestRelTestcaseJoinTestcase(){
        List<TestRelTestcaseJoinTestcase> list = this.findTestRelTestcaseService.findTestRelTestcaseJoinTestcase(TEST_GUID);
        assertEquals(1, list.stream().count());
        TestRelTestcaseJoinTestcase instance = list.get(0);
        assertEquals(RELATION_GUID, instance.getRelationGuid());
        assertEquals(TESTCASE_GUID, instance.getTestcaseGuid());
        assertEquals(TEST_GUID, instance.getTestGuid());
    }

    @Autowired
    private FindTestService findTestService;
    @Test
    public void findTest() {
        KtsTest test = this.findTestService.findTest(TEST_GUID);
        assertEquals(TEST_GUID, test.getTestGuid());
        assertEquals(TEST_NAME, test.getName());
        assertEquals(TEST_DESCRIPTION, test.getDescription());
        assertEquals("N", test.getDeleted());
    }
    @Test
    public void findAllTest() {
        List<KtsTest> list = this.findTestService.findAllTest();
        KtsTest test = null;
        for(int i = 0; i < list.stream().count(); i++){
            if(list.get(i).getTestGuid().equals(TEST_GUID)){
                test = list.get(i);
                break;
            }
        }
        assertFalse(test == null);
        assertEquals(TEST_GUID, test.getTestGuid());
        assertEquals(TEST_NAME, test.getName());
        assertEquals(TEST_DESCRIPTION, test.getDescription());
        assertEquals("N", test.getDeleted());
    }

    @Autowired
    private RemoveTestService removeTestService;
    @Test
    public void removeTest(){
        KtsTest test = this.findTestService.findTest(TEST_GUID);
        assertEquals(TEST_GUID, test.getTestGuid());
        assertEquals(TEST_NAME, test.getName());
        assertEquals(TEST_DESCRIPTION, test.getDescription());
        assertEquals("N", test.getDeleted());

        RemoveTestCommand removeTestCommand = new RemoveTestCommand();
        removeTestCommand.setTestGuid(TEST_GUID);
        this.removeTestService.removeTest(removeTestCommand);

        test = this.findTestService.findTest(TEST_GUID);
        assertTrue(test == null);
    }

    @Autowired
    private SaveTestRelTestcaseService saveTestRelTestcaseService;
    @Test
    public void saveTestRelTestcase(){
        SaveTestRelTestcaseCommand command = new SaveTestRelTestcaseCommand();
        List<TestRelTestcase> relList = new ArrayList<>();
        TestRelTestcase testRelTestcase = new TestRelTestcase();
        testRelTestcase.setRelationGuid(RELATION_GUID);
        testRelTestcase.setTestGuid(TEST_GUID);
        testRelTestcase.setListIndex(0);
        testRelTestcase.setTestcaseGuid(TESTCASE_GUID);
        testRelTestcase.setDeleted("N");
        relList.add(testRelTestcase);
        command.setTestGuid(TEST_GUID);
        command.setRelationList(relList);
        this.saveTestRelTestcaseService.saveTestRelTestcase(command);

        List<TestRelTestcaseJoinTestcase> list = this.findTestRelTestcaseService.findTestRelTestcaseJoinTestcase(TEST_GUID);
        assertEquals(1, list.stream().count());
        assertEquals(RELATION_GUID, list.get(0).getRelationGuid());
    }
    //endregion

    //region service-testcase
    @Autowired
    private FindActionPropertyService findActionPropertyService;
    @Test
    public void findProperties(){
        Map<String, List<KtsActionProperty>> properties = this.findActionPropertyService.findProperties(TESTCASE_GUID);

    }

    @Autowired
    private FindActionService findActionService;
    @Test
    public void getActionTemplate() {

    }
    @Test
    public void findAction() {

    }

    @Autowired
    private FindPropertyService findPropertyService;
    @Test
    public void getPropertyTemplate(){

    }

    @Autowired
    private FindTestcaseService findTestcaseService;
    @Test
    public void findTestcase() {

    }
    @Test
    public void findAllTestcase() {

    }

    @Autowired
    private RemoveTestcaseService removeTestcaseService;
    @Test
    public void removeTestcase(){

    }

    @Autowired
    private SaveTestcaseService saveTestcaseService;
    @Test
    public void saveTestcase(){

    }
    //endregion
}
