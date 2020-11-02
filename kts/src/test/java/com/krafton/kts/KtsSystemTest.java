package com.krafton.kts;

import com.krafton.kts.domains.derived.TestRelTestcaseJoinTestcase;
import com.krafton.kts.domains.entity.*;
import com.krafton.kts.interfaces.repository.action.ActionInterface;
import com.krafton.kts.interfaces.repository.derived.DerivedDomainInterface;
import com.krafton.kts.interfaces.repository.derived.NextTestInstructionResponse;
import com.krafton.kts.interfaces.repository.derived.RunTestCommand;
import com.krafton.kts.interfaces.repository.derived.SaveTestcaseCommand;
import com.krafton.kts.interfaces.repository.property.FindPropertiesCommand;
import com.krafton.kts.interfaces.repository.property.PropertyInterface;
import com.krafton.kts.interfaces.repository.runningaction.RunningActionInterface;
import com.krafton.kts.interfaces.repository.runningproperty.RunningPropertyInterface;
import com.krafton.kts.interfaces.repository.runningtest.RunningTestInterface;
import com.krafton.kts.interfaces.repository.runningtestcase.RunningTestcaseInterface;
import com.krafton.kts.interfaces.repository.test.RemoveTestCommand;
import com.krafton.kts.interfaces.repository.test.TestInterface;
import com.krafton.kts.interfaces.repository.testcase.RemoveTestcaseCommand;
import com.krafton.kts.interfaces.repository.testcase.TestcaseInterface;
import com.krafton.kts.interfaces.repository.testreltestcase.SaveTestRelTestcaseCommand;
import com.krafton.kts.interfaces.repository.testreltestcase.TestRelTestcaseInterface;
import com.krafton.kts.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = KtsSystem.class)
public class KtsSystemTest {

    private final String TESTCASE_GUID = "testcaseGuid1";
    private final String TEST_GUID = "testGuid1";
    private final String ACTION_ID = "actionId1";
    private final String ACTION_GUID = "actionGuid1";
    private final String RELATION_GUID = "relationGuid1";
    private final String RUNNING_TEST_GUID = "runningTestGuid1";
    private final String RUNNING_ACTION_GUID = "runningActionGuid1";
    private final String RUNNING_PROPERTY_GUID = "runningPropertyGuid1";
    private final String RUNNING_TESTCASE_GUID = "runningTestcaseGuid1";

    //Repository 스파이 래핑
    @SpyBean
    private ActionInterface actionInterface;
    @SpyBean
    private DerivedDomainInterface derivedDomainInterface;
    @SpyBean
    private PropertyInterface propertyInterface;
    @SpyBean
    private RunningActionInterface runningActionInterface;
    @SpyBean
    private RunningPropertyInterface runningPropertyInterface;
    @SpyBean
    private RunningTestcaseInterface runningTestcaseInterface;
    @SpyBean
    private RunningTestInterface runningTestInterface;
    @SpyBean
    private TestcaseInterface testcaseInterface;
    @SpyBean
    private TestInterface testInterface;
    @SpyBean
    private TestRelTestcaseInterface testRelTestcaseInterface;

    @BeforeEach
    public void beforeEach(){
        //자료 생성
        List<KtsActionTemplate> actionTemplateList = new ArrayList<>();
        KtsActionTemplate actionTemplate = new KtsActionTemplate();
        actionTemplate.setActionId(ACTION_ID);
        actionTemplateList.add(actionTemplate);

        List<KtsTestcase> testcaseList = new ArrayList<>();
        KtsTestcase testcase = new KtsTestcase();
        testcase.setTestcaseGuid(TESTCASE_GUID);
        testcaseList.add(testcase);

        List<KtsAction> actionList = new ArrayList<>();
        KtsAction action = new KtsAction();
        action.setActionGuid(ACTION_GUID);
        actionList.add(action);

        List<TestRelTestcaseJoinTestcase> testRelTestcaseJoinTestcaseList = new ArrayList<>();
        TestRelTestcaseJoinTestcase testRelTestcaseJoinTestcase = new TestRelTestcaseJoinTestcase();
        testRelTestcaseJoinTestcase.setRelationGuid(RELATION_GUID);
        testRelTestcaseJoinTestcaseList.add(testRelTestcaseJoinTestcase);

        List<KtsActionPropertyTemplate> actionPropertyTemplateList = new ArrayList<>();
        KtsActionPropertyTemplate actionPropertyTemplate = new KtsActionPropertyTemplate();
        actionPropertyTemplate.setActionId(ACTION_ID);
        actionPropertyTemplateList.add(actionPropertyTemplate);

        List<String> actionGuidList = new ArrayList<>();
        actionGuidList.add(ACTION_GUID);

        List<KtsActionProperty> actionPropertyList = new ArrayList<>();
        KtsActionProperty actionProperty = new KtsActionProperty();
        actionProperty.setActionGuid(ACTION_GUID);
        actionPropertyList.add(actionProperty);

        List<RunningAction> runningActionList = new ArrayList<>();
        RunningAction runningAction = new RunningAction();
        runningAction.setRunningActionGuid(RUNNING_ACTION_GUID);
        runningActionList.add(runningAction);

        List<RunningProperty> runningPropertyList = new ArrayList<>();
        RunningProperty runningProperty = new RunningProperty();
        runningProperty.setRunningPropertyGuid(RUNNING_PROPERTY_GUID);
        runningPropertyList.add(runningProperty);

        List<RunningTestcase> runningTestcaseList = new ArrayList<>();
        RunningTestcase runningTestcase = new RunningTestcase();
        runningTestcase.setRunningTestcaseGuid(RUNNING_TESTCASE_GUID);
        runningTestcaseList.add(runningTestcase);

        List<RunningTest> runningTestList = new ArrayList<>();
        RunningTest runningTest = new RunningTest();
        runningTest.setRunningTestGuid(RUNNING_TEST_GUID);
        runningTestList.add(runningTest);

        List<KtsTest> testList = new ArrayList<>();
        KtsTest test = new KtsTest();
        test.setTestGuid(TEST_GUID);
        testList.add(test);

        given(this.actionInterface.getActionTemplate()).willReturn(actionTemplateList);
        given(this.actionInterface.findAction(TESTCASE_GUID)).willReturn(actionList);
        given(this.derivedDomainInterface.findTestRelTestcaseJoinTestcase(TEST_GUID)).willReturn(testRelTestcaseJoinTestcaseList);
        given(this.propertyInterface.getPropertyTemplate(ACTION_ID)).willReturn(actionPropertyTemplateList);
        given(this.propertyInterface.findProperties(new FindPropertiesCommand(actionGuidList))).willReturn(actionPropertyList);
        given(this.runningActionInterface.findRunningAction(RUNNING_TEST_GUID)).willReturn(runningActionList);
        given(this.runningPropertyInterface.findRunningProperty(RUNNING_ACTION_GUID)).willReturn(runningPropertyList);
        given(this.runningTestcaseInterface.findRunningTestcase(RUNNING_TEST_GUID)).willReturn(runningTestcaseList);
        given(this.runningTestInterface.findAllRunningTest()).willReturn(runningTestList);
        given(this.runningTestInterface.findRunningTest(RUNNING_TEST_GUID)).willReturn(runningTest);
        given(this.testcaseInterface.findAll()).willReturn(testcaseList);
        given(this.testcaseInterface.findTestcase(TESTCASE_GUID)).willReturn(testcase);
        given(this.testInterface.findAllTest()).willReturn(testList);
        given(this.testInterface.findTest(TEST_GUID)).willReturn(test);
    }

    //service-dashboard
    @Autowired
    private FindRunningTestService findRunningTestService;
    @Test
    public void findAllRunningTest() {
        this.findRunningTestService.findAllRunningTest();
    }

    @Autowired
    private TestManagingService testManagingService;
    @Test
    public void runTest(){

    }
    @Test
    public void onActionFinished(){

    }

    //service-test
    @Autowired
    private FindTestRelTestcaseService findTestRelTestcaseService;
    @Test
    public void findTestRelTestcaseJoinTestcase(){

    }

    @Autowired
    private FindTestService findTestService;
    @Test
    public void findTest() {

    }
    @Test
    public void findAllTest() {

    }

    @Autowired
    private RemoveTestService removeTestService;
    @Test
    public void removeTest(){

    }

    @Autowired
    private SaveTestRelTestcaseService saveTestRelTestcaseService;
    @Test
    public void saveTestRelTestcase(){

    }

    //service-testcase
    @Autowired
    private FindActionPropertyService findActionPropertyService;
    @Test
    public void findProperties(){

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
}
