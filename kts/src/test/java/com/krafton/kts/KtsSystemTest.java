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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {
        FindRunningTestService.class,
        TestManagingService.class,
        FindTestRelTestcaseService.class,
        FindTestService.class,
        RemoveTestService.class,
        SaveTestRelTestcaseService.class,
        FindActionPropertyService.class,
        FindActionService.class,
        FindPropertyService.class,
        FindTestcaseService.class,
        RemoveTestcaseService.class,
        SaveTestcaseService.class,
})
public class KtsSystemTest {

    //region 테스트 준비
    private final String TESTCASE_GUID = "testcaseGuid1";
    private final String TEST_GUID = "testGuid1";
    private final String ACTION_ID = "actionId1";
    private final String ACTION_GUID = "actionGuid1";
    private final String RELATION_GUID = "relationGuid1";
    private final String RUNNING_TEST_GUID = "runningTestGuid1";
    private final String RUNNING_ACTION_GUID = "runningActionGuid1";
    private final String RUNNING_PROPERTY_GUID = "runningPropertyGuid1";
    private final String RUNNING_TESTCASE_GUID = "runningTestcaseGuid1";

    //Repository는 Mock객체 사용
    @MockBean
    private ActionInterface actionInterface;
    @MockBean
    private DerivedDomainInterface derivedDomainInterface;
    @MockBean
    private PropertyInterface propertyInterface;
    @MockBean
    private RunningActionInterface runningActionInterface;
    @MockBean
    private RunningPropertyInterface runningPropertyInterface;
    @MockBean
    private RunningTestcaseInterface runningTestcaseInterface;
    @MockBean
    private RunningTestInterface runningTestInterface;
    @MockBean
    private TestcaseInterface testcaseInterface;
    @MockBean
    private TestInterface testInterface;
    @MockBean
    private TestRelTestcaseInterface testRelTestcaseInterface;

    List<KtsActionTemplate> actionTemplateList;
    KtsActionTemplate actionTemplate;
    List<KtsTestcase> testcaseList;
    KtsTestcase testcase;
    List<KtsAction> actionList;
    KtsAction action;
    List<TestRelTestcaseJoinTestcase> testRelTestcaseJoinTestcaseList;
    TestRelTestcaseJoinTestcase testRelTestcaseJoinTestcase;
    List<KtsActionPropertyTemplate> actionPropertyTemplateList;
    KtsActionPropertyTemplate actionPropertyTemplate;
    List<String> actionGuidList;
    List<KtsActionProperty> actionPropertyList;
    KtsActionProperty actionProperty;
    List<RunningAction> runningActionList;
    RunningAction runningAction;
    List<RunningProperty> runningPropertyList;
    RunningProperty runningProperty;
    List<RunningTestcase> runningTestcaseList;
    RunningTestcase runningTestcase;
    List<RunningTest> runningTestList;
    RunningTest runningTest;
    List<KtsTest> testList;
    KtsTest test;

    @BeforeEach
    public void beforeEach(){
        //자료 생성
        actionTemplateList = new ArrayList<>();
        actionTemplate = new KtsActionTemplate();
        actionTemplate.setActionId(ACTION_ID);
        actionTemplateList.add(actionTemplate);

        testcaseList = new ArrayList<>();
        testcase = new KtsTestcase();
        testcase.setTestcaseGuid(TESTCASE_GUID);
        testcaseList.add(testcase);

        actionList = new ArrayList<>();
        action = new KtsAction();
        action.setActionGuid(ACTION_GUID);
        actionList.add(action);

        testRelTestcaseJoinTestcaseList = new ArrayList<>();
        testRelTestcaseJoinTestcase = new TestRelTestcaseJoinTestcase();
        testRelTestcaseJoinTestcase.setRelationGuid(RELATION_GUID);
        testRelTestcaseJoinTestcaseList.add(testRelTestcaseJoinTestcase);

        actionPropertyTemplateList = new ArrayList<>();
        actionPropertyTemplate = new KtsActionPropertyTemplate();
        actionPropertyTemplate.setActionId(ACTION_ID);
        actionPropertyTemplateList.add(actionPropertyTemplate);

        actionGuidList = new ArrayList<>();
        actionGuidList.add(ACTION_GUID);

        actionPropertyList = new ArrayList<>();
        actionProperty = new KtsActionProperty();
        actionProperty.setActionGuid(ACTION_GUID);
        actionPropertyList.add(actionProperty);

        runningActionList = new ArrayList<>();
        runningAction = new RunningAction();
        runningAction.setRunningActionGuid(RUNNING_ACTION_GUID);
        runningActionList.add(runningAction);

        runningPropertyList = new ArrayList<>();
        runningProperty = new RunningProperty();
        runningProperty.setRunningPropertyGuid(RUNNING_PROPERTY_GUID);
        runningPropertyList.add(runningProperty);

        runningTestcaseList = new ArrayList<>();
        runningTestcase = new RunningTestcase();
        runningTestcase.setRunningTestcaseGuid(RUNNING_TESTCASE_GUID);
        runningTestcaseList.add(runningTestcase);

        runningTestList = new ArrayList<>();
        runningTest = new RunningTest();
        runningTest.setRunningTestGuid(RUNNING_TEST_GUID);
        runningTestList.add(runningTest);

        testList = new ArrayList<>();
        test = new KtsTest();
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
    //endregion

    //region service-dashboard
    @Autowired
    private FindRunningTestService findRunningTestService;
    @Test
    public void findAllRunningTest() {
        List<RunningTest> res = this.findRunningTestService.findAllRunningTest();
        assertEquals(runningTestList.stream().count(), res.stream().count());
        for(int i = 0; i < runningTestList.stream().count(); i++){
            assertEquals(runningTestList.get(i).getRunningTestGuid(), res.get(i).getRunningTestGuid());
        }
    }

    @Autowired
    private TestManagingService testManagingService;
    @Test
    public void runTest(){
        //테스트가 실제로 실행되고 정보가 DB에 남는지를 봐야 하는 로직이라 테스트가 곤란함. (4개 테이블 사용)
        //이 기능 자체를 여러개의 기능으로 쪼개서 유닛테스트가 가능하게 할 수도 있겠지만,
        //실제로는 다 트랜잭셔널한 내용인데 테스트를 위해 쪼개는것은 큰 의미가 없어 보임.
        //이런식의 거대한 하나의 기능이 마음에 들지 않는다면, 애초에 DB설계시부터 이런 내용을 반영해야 할 듯.
        //사실 지금도 running_XXX로 된 DB들은 전부 runTest,onActionFinished에서밖에 사용되지 않는다.
        //근데 이게 4개의 도메인을 차지하고 있어서 테스트가 거대해졌다. 꼭 'DB'구조 그대로 '도메인'을 쪼갤 필요는 없는것으로 보인다.
    }
    @Test
    public void onActionFinished(){
        //상기한 이유로 생략.
    }
    //endregion

    //region service-test
    @Autowired
    private FindTestRelTestcaseService findTestRelTestcaseService;
    @Test
    public void findTestRelTestcaseJoinTestcase(){
        List<TestRelTestcaseJoinTestcase> list = new ArrayList<>();
        TestRelTestcaseJoinTestcase testRelTestcaseJoinTestcase = new TestRelTestcaseJoinTestcase();
        testRelTestcaseJoinTestcase.setRelationGuid(RELATION_GUID);
        list.add(testRelTestcaseJoinTestcase);
        given(this.derivedDomainInterface.findTestRelTestcaseJoinTestcase(TEST_GUID)).willReturn(list);

        List<TestRelTestcaseJoinTestcase> res = this.findTestRelTestcaseService.findTestRelTestcaseJoinTestcase(TEST_GUID);

        assertEquals(list.stream().count(), res.stream().count());
        for(int i = 0; i < list.stream().count(); i++){
            assertEquals(list.get(i).getRelationGuid(), res.get(i).getRelationGuid());
        }
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
    //endregion

    //region service-testcase
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
    //endregion
}
