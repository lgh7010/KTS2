package com.krafton.kts.backend.service;

import com.krafton.kts.backend.domain.action.domain.command.*;
import com.krafton.kts.backend.domain.action.domain.db.KTS_ACTION;
import com.krafton.kts.backend.domain.property.domain.command.FindPropertiesCommand;
import com.krafton.kts.backend.domain.property.domain.command.RemovePropertiesCommand;
import com.krafton.kts.backend.domain.property.domain.command.SavePropertiesCommand;
import com.krafton.kts.backend.domain.property.domain.db.KTS_ACTION_PROPERTY;
import com.krafton.kts.backend.domain.property.domain.db.KTS_ACTION_PROPERTY_TEMPLATE;
import com.krafton.kts.backend.domain.action.domain.db.KTS_ACTION_TEMPLATE;
import com.krafton.kts.backend.domain.action.interfaces.ActionInterface;
import com.krafton.kts.backend.domain.property.interfaces.PropertyInterface;
import com.krafton.kts.backend.domain.running_action.domain.command.AddRunningActionCommand;
import com.krafton.kts.backend.domain.running_action.domain.db.RUNNING_ACTION;
import com.krafton.kts.backend.domain.running_action.interfaces.RunningActionInterface;
import com.krafton.kts.backend.domain.running_property.domain.command.AddRunningPropertyCommand;
import com.krafton.kts.backend.domain.running_property.domain.db.RUNNING_PROPERTY;
import com.krafton.kts.backend.domain.running_property.interfaces.RunningPropertyInterface;
import com.krafton.kts.backend.domain.running_test.domain.db.RUNNING_TEST;
import com.krafton.kts.backend.domain.running_test.interfaces.RunningTestInterface;
import com.krafton.kts.backend.domain.running_testcase.domain.command.AddRunningTestcaseCommand;
import com.krafton.kts.backend.domain.running_testcase.domain.db.RUNNING_TESTCASE;
import com.krafton.kts.backend.domain.running_testcase.interfaces.RunningTestcaseInterface;
import com.krafton.kts.backend.domain.test.domain.command.AddTestCommand;
import com.krafton.kts.backend.domain.test.domain.command.RemoveTestCommand;
import com.krafton.kts.backend.domain.test.domain.db.KTS_TEST;
import com.krafton.kts.backend.domain.test.interfaces.TestInterface;
import com.krafton.kts.backend.domain.test_rel_testcase.domain.command.RemoveTestRelTestcaseByTestGuidCommand;
import com.krafton.kts.backend.domain.test_rel_testcase.domain.command.RemoveTestRelTestcaseByTestcaseGuidCommand;
import com.krafton.kts.backend.domain.test_rel_testcase.domain.command.SaveTestRelTestcaseCommand;
import com.krafton.kts.backend.service.crossdomain.command.FindRunningActionCommand;
import com.krafton.kts.backend.service.crossdomain.command.OnFinishActionCommand;
import com.krafton.kts.backend.service.crossdomain.command.RunTestCommnad;
import com.krafton.kts.backend.service.crossdomain.db.TEST_REL_TESTCASE_JOIN_TESTCASE;
import com.krafton.kts.backend.domain.test_rel_testcase.interfaces.TestRelTestcaseInterface;
import com.krafton.kts.backend.domain.testcase.domain.command.RemoveTestcaseCommand;
import com.krafton.kts.backend.domain.testcase.domain.db.KTS_TESTCASE;
import com.krafton.kts.backend.domain.testcase.interfaces.TestcaseInterface;
import com.krafton.kts.backend.service.crossdomain.command.SaveTestcaseCommand;
import com.krafton.kts.backend.service.crossdomain.response.NextTestInstructionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
public class KTSServiceImpl implements KTSService {

    private final ActionInterface actionInterface;
    private final PropertyInterface propertyInterface;
    private final TestcaseInterface testcaseInterface;
    private final TestRelTestcaseInterface testRelTestcaseInterface;
    private final TestInterface testInterface;
    private final RunningTestInterface runningTestInterface;
    private final RunningTestcaseInterface runningTestcaseInterface;
    private final RunningActionInterface runningActionInterface;
    private final RunningPropertyInterface runningPropertyInterface;

    //transactional
    @Override
    @Transactional
    public void saveTestcase(SaveTestcaseCommand command) {
        try {
            if(command.getCurrentTestcaseActions().size() > 0){
                this.actionInterface.saveAction(new SaveActionCommand(command.getCurrentTestcaseActions(), command.getRemoveActionGuidList()));

                Map<String, List<KTS_ACTION_PROPERTY>> currentTestcaseActionPropertyMap = command.getCurrentTestcaseActionPropertyMap();
                List<KTS_ACTION_PROPERTY> finalProperties = new ArrayList<>();
                List<String> actionGuids = new ArrayList<>();
                currentTestcaseActionPropertyMap.forEach((actionGuid, properties) -> {
                    actionGuids.add(actionGuid);
                    for (KTS_ACTION_PROPERTY property : properties) {
                        finalProperties.add(property);
                    }
                });
                if(actionGuids.stream().count() > 0){
                    this.propertyInterface.saveProperties(new SavePropertiesCommand(finalProperties, actionGuids));
                }
            }
            this.testcaseInterface.addTestcase(command.getTestcase());
        } catch(Exception e){
            throw e;
        }
    }
    @Override
    @Transactional
    public void removeTestcase(RemoveTestcaseCommand command) {
        try {
            this.testcaseInterface.removeTestcase(command);
            this.actionInterface.removeAction(new RemoveActionCommand(command.getTestcaseGuid()));
            this.testRelTestcaseInterface.removeTestRelTestcaseByTestcaseGuid(new RemoveTestRelTestcaseByTestcaseGuidCommand(command.getTestcaseGuid()));
            this.propertyInterface.removeProperties(new RemovePropertiesCommand(command.getTestcaseGuid()));
        } catch(Exception e){
            throw e;
        }
    }
    @Override
    @Transactional
    public void removeTest(RemoveTestCommand command) {
        try {
            this.testInterface.removeTest(command);
            this.testRelTestcaseInterface.removeTestRelTestcaseByTestGuid(new RemoveTestRelTestcaseByTestGuidCommand(command.getTestGuid()));
        } catch (Exception e){
            throw e;
        }
    }
    @Override
    @Transactional
    public void saveTestRelTestcase(SaveTestRelTestcaseCommand command) {
        try {
            if(command.getRelationList().stream().count() > 0){
                this.testRelTestcaseInterface.saveTestRelTestcase(command);
            }
            this.testInterface.addTest(new AddTestCommand(command.getTestGuid(), command.getTestName(), command.getTestDescription()));
        } catch(Exception e){
            throw e;
        }
    }

    @Override
    @Transactional
    public NextTestInstructionResponse runTest(RunTestCommnad command) {
        try {
            String runningTestGuid = UUID.randomUUID().toString();

            //step 1. 실행하고자 하는 테스트 정보를 확보하고, 이를 RunningTest에 추가한다.
            KTS_TEST test = this.testInterface.findTest(command.getTestGuid());
            RUNNING_TEST runningTest = new RUNNING_TEST();
            runningTest.setTestGuid(test.getTestGuid());
            runningTest.setRunningTestGuid(runningTestGuid);

            //step 2. 해당 테스트에 포함된 테스트케이스 정보를 확보하고, 이를 RunningTestcase에 추가한다.
            List<TEST_REL_TESTCASE_JOIN_TESTCASE> testcaseJoinTestcaseList = this.testRelTestcaseInterface.findTestRelTestcaseJoinTestcase(test.getTestGuid());
            List<RUNNING_TESTCASE> runningTestcases = new ArrayList<>();
            for (TEST_REL_TESTCASE_JOIN_TESTCASE testRelTestcaseJoinTestcase : testcaseJoinTestcaseList) {
                String runningTestcaseGuid = UUID.randomUUID().toString();

                RUNNING_TESTCASE runningTestcase = new RUNNING_TESTCASE();
                runningTestcase.setRunningTestcaseGuid(runningTestcaseGuid);
                runningTestcase.setTestcaseGuid(testRelTestcaseJoinTestcase.getTestcaseGuid());
                runningTestcase.setRunningTestGuid(runningTestGuid);
                runningTestcase.setRunningTestcaseOrder(testRelTestcaseJoinTestcase.getListIndex());
                runningTestcases.add(runningTestcase);

                //step 3. 해당 테스트에 포함된 액션 정보와 프로퍼티 정보를 확인하고, 이를 RunningAction와 RunningProperties에 추가한다.
                List<KTS_ACTION> actionList = this.actionInterface.findAction(testRelTestcaseJoinTestcase.getTestcaseGuid());
                List<String> actionGuids = new ArrayList<>();
                for (KTS_ACTION action : actionList) {
                    actionGuids.add(action.getActionGuid());
                }
                Map<String, KTS_ACTION> actionMap = new HashMap<>();
                for (KTS_ACTION action : actionList) {
                    actionMap.put(action.getActionGuid(), action);
                }
                List<KTS_ACTION_PROPERTY> propertyList = this.propertyInterface.findProperties(new FindPropertiesCommand(actionGuids));

                List<RUNNING_ACTION> runningActions = new ArrayList<>();
                List<RUNNING_PROPERTY> runningProperties = new ArrayList<>();
                Map<String, String> actionGuidRunningActionGuidMatching = new HashMap<>();
                //첫번째 액션을 찾는다.
                KTS_ACTION currentAction = null;
                for(KTS_ACTION action : actionList){
                    System.out.println(action);
                    if(action.getIsStart().equals("Y")){
                        currentAction = action;
                        break;
                    }
                }
                System.out.println(currentAction);
                int actionOrder = 0;
                while(currentAction != null){
                    //현재 액션을 RUNNING액션에 추가
                    String runningActionGuid = UUID.randomUUID().toString();
                    actionGuidRunningActionGuidMatching.put(currentAction.getActionGuid(), runningActionGuid);

                    RUNNING_ACTION runningAction = new RUNNING_ACTION();
                    runningAction.setRunningActionGuid(runningActionGuid);
                    runningAction.setRunningTestcaseGuid(runningTestcaseGuid);
                    runningAction.setActionId(currentAction.getActionId());
                    runningAction.setActionOrder(actionOrder++);
                    runningActions.add(runningAction);

                    currentAction = actionMap.get(currentAction.getNextActionGuid());
                }
                for (KTS_ACTION_PROPERTY property : propertyList) {
                    String runningPropertyGuid = UUID.randomUUID().toString();

                    RUNNING_PROPERTY runningProperty = new RUNNING_PROPERTY();
                    runningProperty.setRunningPropertyGuid(runningPropertyGuid);
                    runningProperty.setRunningActionGuid(actionGuidRunningActionGuidMatching.get(property.getActionGuid()));
                    runningProperty.setRunningPropertyName(property.getPropertyName());
                    runningProperty.setRunningPropertyValue(property.getPropertyValue());

                    runningProperties.add(runningProperty);
                }

                System.out.println("add runningActions : " + runningActions);
                System.out.println("add runningProperties : " + runningProperties);
                this.runningActionInterface.addRunningAction(new AddRunningActionCommand(runningActions));
                this.runningPropertyInterface.addRunningProperty(new AddRunningPropertyCommand(runningProperties));
            }

            System.out.println("add runningTestcase : " + runningTestcases);
            System.out.println("addUpdate runningTest : " + runningTest);
            this.runningTestcaseInterface.addRunningTestcase(new AddRunningTestcaseCommand(runningTestcases));
            this.runningTestInterface.addOrUpdateRunningTest(runningTest);

            //running에 넣은 값에서 '첫번째'액션의 정보를 클라이언트에 응답한다.
            NextTestInstructionResponse response = new NextTestInstructionResponse();
            RUNNING_ACTION runningAction = this.findRunningAction(new FindRunningActionCommand(runningTestGuid, 0, 0));
            response.setRunningTest(runningTest);
            response.setRunningAction(runningAction);
            response.setRunningProperties(this.runningPropertyInterface.findRunningProperty(runningAction.getRunningActionGuid()));
            return response;
        } catch(Exception e){
            throw e;
        }
    }
    @Override
    @Transactional
    public NextTestInstructionResponse onFinishAction(OnFinishActionCommand command) {
        NextTestInstructionResponse nextTestInstructionResponse = new NextTestInstructionResponse();

        //step 1. RUNNING_TEST DB의 currentRunningTestcaseOrder, currentRunningActionOrder 정보를 업데이트한다.
        RUNNING_TEST runningTest = this.runningTestInterface.findRunningTest(command.getRunningTestGuid());
        List<RUNNING_TESTCASE> runningTestcases = this.runningTestcaseInterface.findRunningTestcase(runningTest.getRunningTestGuid());
        RUNNING_TESTCASE runningTestcase = runningTestcases.get(command.getCurrentRunningTestcaseOrder());
        List<RUNNING_ACTION> runningActions = this.runningActionInterface.findRunningAction(runningTestcase.getRunningTestcaseGuid());
        if(command.getCurrentRunningActionOrder() >= runningActions.stream().count() - 1){
            //액션리스트가 끝났으면, 테스트케이스 오더를 1 올린다.
            if(runningTest.getCurrentRunningTestcaseOrder() >= runningTestcases.stream().count() - 1){
                //테스트케이스 리스트도 끝났으면 테스트 완료 처리
                nextTestInstructionResponse.setRunningTest(runningTest);
                nextTestInstructionResponse.setIsFinished("Y");
                runningTest.setEndAt(Timestamp.valueOf(LocalDateTime.now()));
                runningTest.setStatus(command.getStatus());
                this.runningTestInterface.addOrUpdateRunningTest(runningTest);
                return nextTestInstructionResponse;
            }
            runningTest.setCurrentRunningTestcaseOrder(runningTest.getCurrentRunningTestcaseOrder() + 1);
            runningTest.setCurrentRunningActionOrder(0);
        } else {
            //액션리스트가 아직 안 끝났으면, 액션 오더를 1 올린다.
            runningTest.setCurrentRunningActionOrder(runningTest.getCurrentRunningActionOrder() + 1);
        }
        this.runningTestInterface.addOrUpdateRunningTest(runningTest);

        //step 2. 해당 액션 정보를 가져와서, 필요한 정보를 넣어 반환한다.
        RUNNING_ACTION nextAction = this.findRunningAction(new FindRunningActionCommand(runningTest.getRunningTestGuid(), runningTest.getCurrentRunningTestcaseOrder(), runningTest.getCurrentRunningActionOrder()));
        nextTestInstructionResponse.setRunningTest(runningTest);
        nextTestInstructionResponse.setRunningAction(nextAction);
        nextTestInstructionResponse.setRunningProperties(this.runningPropertyInterface.findRunningProperty(nextAction.getRunningActionGuid()));
        return nextTestInstructionResponse;
    }
    private RUNNING_ACTION findRunningAction(FindRunningActionCommand command) {
        try {
            //step 1. 현재 runningTest의 currentRunningTestcaseOrder, currentRunningActionOrder 확보
            RUNNING_TEST runningTest = this.runningTestInterface.findRunningTest(command.getRunningTestGuid());

            //step 2. 해당 runningTest에 속하는 runningTestcase들을 runningTestcaseOrder로 정렬해서 가져온다음, currentRunningTestcaseOrder에 해당하는 runningTestcase 확보
            List<RUNNING_TESTCASE> runningTestcases = this.runningTestcaseInterface.findRunningTestcase(runningTest.getRunningTestGuid());
            RUNNING_TESTCASE runningTestcase = runningTestcases.get(command.getCurrentRunningTestcaseOrder());

            //step 3. 해당 runningTestcase에 속하는 runningAction들을 runningActionOrder로 정렬해서 가져온다음, currentRunningActionOrder에 해당하는 runningAction 확보 및 반환
            List<RUNNING_ACTION> runningActions = this.runningActionInterface.findRunningAction(runningTestcase.getRunningTestcaseGuid());
            return runningActions.get(command.getCurrentRunningActionOrder());
        } catch(Exception e){
            throw e;
        }
    }



    //action
    @Override
    public List<KTS_ACTION> findAction(String testcaseGuid) {
        return this.actionInterface.findAction(testcaseGuid);
    }
    @Override
    public Map<String, KTS_ACTION_TEMPLATE> getActionTemplate() {
        List<KTS_ACTION_TEMPLATE> list = this.actionInterface.getActionTemplate();
        Map<String, KTS_ACTION_TEMPLATE> ret = new HashMap<>();
        for (KTS_ACTION_TEMPLATE ktsActionTemplate : list) {
            ret.put(ktsActionTemplate.getActionId(), ktsActionTemplate);
        }
        return ret;
    }

    //property
    @Override
    public List<KTS_ACTION_PROPERTY_TEMPLATE> getPropertyTemplate(String actionId) {
        return this.propertyInterface.getPropertyTemplate(actionId);
    }
    @Override
    public Map<String, List<KTS_ACTION_PROPERTY>> findProperties(String testcaseGuid) {
        List<KTS_ACTION> actions = this.actionInterface.findAction(testcaseGuid);
        List<String> actionGuids = new ArrayList<>();
        for (KTS_ACTION action : actions) {
            actionGuids.add(action.getActionGuid());
        }

        Map<String, List<KTS_ACTION_PROPERTY>> ret = new HashMap<>();
        if(actions.stream().count() > 0){
            List<KTS_ACTION_PROPERTY> list = this.propertyInterface.findProperties(new FindPropertiesCommand(actionGuids));
            for (KTS_ACTION_PROPERTY property : list) {
                if(ret.containsKey(property.getActionGuid()) == false){
                    ret.put(property.getActionGuid(), new ArrayList<>());
                }
                ret.get(property.getActionGuid()).add(property);
            }
        }
        return ret;
    }

    //test
    @Override
    public List<KTS_TEST> findAllTest() {
        return this.testInterface.findAllTest();
    }
    @Override
    public KTS_TEST findTest(String testGuid) {
        return this.testInterface.findTest(testGuid);
    }

    //test_rel_testcase
    @Override
    public List<TEST_REL_TESTCASE_JOIN_TESTCASE> findTestRelTestcaseJoinTestcase(String testGuid) {
        return this.testRelTestcaseInterface.findTestRelTestcaseJoinTestcase(testGuid);
    }

    //testcase
    @Override
    public List<KTS_TESTCASE> findAll() {
        return this.testcaseInterface.findAll();
    }
    @Override
    public KTS_TESTCASE findTestcase(String testcaseGuid) {
        return this.testcaseInterface.findTestcase(testcaseGuid);
    }

}
