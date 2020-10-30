package com.krafton.kts.service;

import com.krafton.kts.domains.derived.TEST_REL_TESTCASE_JOIN_TESTCASE;
import com.krafton.kts.domains.entity.*;
import com.krafton.kts.interfaces.repository.action.ActionInterface;
import com.krafton.kts.interfaces.repository.action.RemoveActionCommand;
import com.krafton.kts.interfaces.repository.action.SaveActionCommand;
import com.krafton.kts.interfaces.repository.action.UpdateActionIdCommand;
import com.krafton.kts.interfaces.repository.derived.DerivedDomainInterface;
import com.krafton.kts.interfaces.repository.derived.OnFinishActionCommand;
import com.krafton.kts.interfaces.repository.derived.RunTestCommand;
import com.krafton.kts.interfaces.repository.property.FindPropertiesCommand;
import com.krafton.kts.interfaces.repository.property.PropertyInterface;
import com.krafton.kts.interfaces.repository.property.RemovePropertiesCommand;
import com.krafton.kts.interfaces.repository.property.SavePropertiesCommand;
import com.krafton.kts.interfaces.repository.runningaction.AddRunningActionCommand;
import com.krafton.kts.interfaces.repository.runningaction.RunningActionInterface;
import com.krafton.kts.interfaces.repository.runningproperty.AddRunningPropertyCommand;
import com.krafton.kts.interfaces.repository.runningproperty.RunningPropertyInterface;
import com.krafton.kts.interfaces.repository.runningtest.RunningTestInterface;
import com.krafton.kts.interfaces.repository.runningtestcase.AddRunningTestcaseCommand;
import com.krafton.kts.interfaces.repository.runningtestcase.RunningTestcaseInterface;
import com.krafton.kts.interfaces.repository.test.AddTestCommand;
import com.krafton.kts.interfaces.repository.test.RemoveTestCommand;
import com.krafton.kts.interfaces.repository.test.TestInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = TestDashboardService.class)
public class TestDashboardServiceTest {

    @MockBean
    TestDashboardService testDashboardService;

    @MockBean
    RunningTestInterface runningTestInterface;
    @MockBean
    TestInterface testInterface;
    @MockBean
    ActionInterface actionInterface;
    @MockBean
    DerivedDomainInterface derivedDomainInterface;
    @MockBean
    RunningActionInterface runningActionInterface;
    @MockBean
    PropertyInterface propertyInterface;
    @MockBean
    RunningPropertyInterface runningPropertyInterface;
    @MockBean
    RunningTestcaseInterface runningTestcaseInterface;

    @BeforeEach
    public void ready(){
        testDashboardService = new TestDashboardService(new RunningTestInterface() {
            @Override
            public void addOrUpdateRunningTest(RUNNING_TEST runningTest) {
                System.out.println("addOrUpdateRunningTest");
            }

            @Override
            public RUNNING_TEST findRunningTest(String runningTestGuid) {
                RUNNING_TEST runningTest = new RUNNING_TEST();
                return runningTest;
            }

            @Override
            public List<RUNNING_TEST> findAllRunningTest() {
                List<RUNNING_TEST> list = new ArrayList<>();
                RUNNING_TEST rt = new RUNNING_TEST();
                list.add(rt);
                return list;
            }
        }, new TestInterface() {
            @Override
            public KTS_TEST findTest(String testGuid) {
                KTS_TEST test = new KTS_TEST();
                return test;
            }

            @Override
            public List<KTS_TEST> findAllTest() {
                List<KTS_TEST> list = new ArrayList<>();
                KTS_TEST test = new KTS_TEST();
                list.add(test);
                return list;
            }

            @Override
            public void removeTest(RemoveTestCommand command) {
                System.out.println("removeTest");
            }

            @Override
            public void addTest(AddTestCommand command) {
                System.out.println("addTest");
            }
        }, new ActionInterface() {
            @Override
            public List<KTS_ACTION> findAction(String testcaseGuid) {
                List<KTS_ACTION> list = new ArrayList<>();
                KTS_ACTION action = new KTS_ACTION();
                action.setIsStart("Y");
                list.add(action);
                return list;
            }

            @Override
            public List<KTS_ACTION_TEMPLATE> getActionTemplate() {
                List<KTS_ACTION_TEMPLATE> list = new ArrayList<>();
                KTS_ACTION_TEMPLATE template = new KTS_ACTION_TEMPLATE();
                list.add(template);
                return list;
            }

            @Override
            public void saveAction(SaveActionCommand command) {
                System.out.println("saveAction");
            }

            @Override
            public void updateActionId(UpdateActionIdCommand command) {
                System.out.println("updateActionId");
            }

            @Override
            public void removeAction(RemoveActionCommand command) {
                System.out.println("removeAction");
            }
        }, new DerivedDomainInterface() {
            @Override
            public List<TEST_REL_TESTCASE_JOIN_TESTCASE> findTestRelTestcaseJoinTestcase(String testGuid) {
                List<TEST_REL_TESTCASE_JOIN_TESTCASE> list = new ArrayList<>();
                TEST_REL_TESTCASE_JOIN_TESTCASE rel = new TEST_REL_TESTCASE_JOIN_TESTCASE();
                list.add(rel);
                return list;
            }
        }, new RunningActionInterface() {
            @Override
            public void addRunningAction(AddRunningActionCommand command) {
                System.out.println("addRunningAction");
            }

            @Override
            public List<RUNNING_ACTION> findRunningAction(String runningTestcaseGuid) {
                List<RUNNING_ACTION> list = new ArrayList<>();
                RUNNING_ACTION action = new RUNNING_ACTION();
                list.add(action);
                return list;
            }
        }, new PropertyInterface() {
            @Override
            public List<KTS_ACTION_PROPERTY> findProperties(FindPropertiesCommand command) {
                List<KTS_ACTION_PROPERTY> list = new ArrayList<>();
                KTS_ACTION_PROPERTY property = new KTS_ACTION_PROPERTY();
                list.add(property);
                return list;
            }

            @Override
            public List<KTS_ACTION_PROPERTY_TEMPLATE> getPropertyTemplate(String actionId) {
                List<KTS_ACTION_PROPERTY_TEMPLATE> list = new ArrayList<>();
                KTS_ACTION_PROPERTY_TEMPLATE template = new KTS_ACTION_PROPERTY_TEMPLATE();
                list.add(template);
                return list;
            }

            @Override
            public void saveProperties(SavePropertiesCommand command) {
                System.out.println("saveProperties");
            }

            @Override
            public void removeProperties(RemovePropertiesCommand command) {
                System.out.println("removeProperties");
            }
        }, new RunningPropertyInterface() {
            @Override
            public void addRunningProperty(AddRunningPropertyCommand command) {
                System.out.println("test");
            }

            @Override
            public List<RUNNING_PROPERTY> findRunningProperty(String runningActionGuid) {
                List<RUNNING_PROPERTY> list = new ArrayList<>();
                RUNNING_PROPERTY property = new RUNNING_PROPERTY();
                list.add(property);
                return list;
            }
        }, new RunningTestcaseInterface() {
            @Override
            public void addRunningTestcase(AddRunningTestcaseCommand command) {
                System.out.println("addRunningTestcase");
            }

            @Override
            public List<RUNNING_TESTCASE> findRunningTestcase(String runningTestGuid) {
                List<RUNNING_TESTCASE> list = new ArrayList<>();
                RUNNING_TESTCASE tc = new RUNNING_TESTCASE();
                list.add(tc);
                return list;
            }
        });
    }

    @Test
    public void findAllRunningTest(){
        System.out.println("findAllRunningTest : " + testDashboardService.findAllRunningTest());
    }
    @Test
    public void RunTestCommand(){
        System.out.println("RunTestCommand : " + testDashboardService.runTest(new RunTestCommand()));
    }
    @Test
    public void OnFinishActionCommand(){
        System.out.println("OnFinishActionCommand : " + testDashboardService.onFinishAction(new OnFinishActionCommand()));
    }
}
