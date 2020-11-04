package com.krafton.kts.service;

import com.krafton.kts.domains.derived.TestRelTestcaseJoinTestcase;
import com.krafton.kts.domains.entity.*;
import com.krafton.kts.interfaces.repository.action.ActionInterface;
import com.krafton.kts.interfaces.repository.derived.*;
import com.krafton.kts.interfaces.repository.property.FindPropertiesCommand;
import com.krafton.kts.interfaces.repository.property.PropertyInterface;
import com.krafton.kts.interfaces.repository.runningaction.AddRunningActionCommand;
import com.krafton.kts.interfaces.repository.runningaction.RunningActionInterface;
import com.krafton.kts.interfaces.repository.runningproperty.AddRunningPropertyCommand;
import com.krafton.kts.interfaces.repository.runningproperty.RunningPropertyInterface;
import com.krafton.kts.interfaces.repository.runningtest.RunningTestInterface;
import com.krafton.kts.interfaces.repository.runningtestcase.AddRunningTestcaseCommand;
import com.krafton.kts.interfaces.repository.runningtestcase.RunningTestcaseInterface;
import com.krafton.kts.interfaces.repository.test.TestInterface;
import com.krafton.kts.interfaces.service.TestManagingServiceInterface;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TestManagingService implements TestManagingServiceInterface {

    private final RunningTestInterface runningTestInterface;
    private final TestInterface testInterface;
    private final ActionInterface actionInterface;
    private final DerivedDomainInterface derivedDomainInterface;
    private final RunningActionInterface runningActionInterface;
    private final PropertyInterface propertyInterface;
    private final RunningPropertyInterface runningPropertyInterface;
    private final RunningTestcaseInterface runningTestcaseInterface;

    @Override
    @Transactional
    public NextTestInstructionResponse runTest(RunTestCommand command) {
        try {
            String runningTestGuid = UUID.randomUUID().toString();

            //step 1. 실행하고자 하는 테스트 정보를 확보하고, 이를 RunningTest에 추가한다.
            KtsTest test = this.testInterface.findTest(command.getTestGuid());
            RunningTest runningTest = new RunningTest();
            runningTest.setTestGuid(test.getTestGuid());
            runningTest.setRunningTestGuid(runningTestGuid);
            runningTest.setCurrentRunningActionOrder(0);
            runningTest.setCurrentRunningTestcaseOrder(0);
            runningTest.setName(test.getName());
            runningTest.setDescription(test.getDescription());
            runningTest.setStatus("Running");
            runningTest.setStartAt(Timestamp.valueOf(LocalDateTime.now()));

            //step 2. 해당 테스트에 포함된 테스트케이스 정보를 확보하고, 이를 RunningTestcase에 추가한다.
            List<TestRelTestcaseJoinTestcase> testcaseJoinTestcaseList = this.derivedDomainInterface.findTestRelTestcaseJoinTestcase(test.getTestGuid());
            List<RunningTestcase> runningTestcases = new ArrayList<>();
            for (TestRelTestcaseJoinTestcase testRelTestcaseJoinTestcase : testcaseJoinTestcaseList) {
                String runningTestcaseGuid = UUID.randomUUID().toString();

                RunningTestcase runningTestcase = new RunningTestcase();
                runningTestcase.setRunningTestcaseGuid(runningTestcaseGuid);
                runningTestcase.setTestcaseGuid(testRelTestcaseJoinTestcase.getTestcaseGuid());
                runningTestcase.setRunningTestGuid(runningTestGuid);
                runningTestcase.setRunningTestcaseOrder(testRelTestcaseJoinTestcase.getListIndex());
                runningTestcases.add(runningTestcase);

                //step 3. 해당 테스트에 포함된 액션 정보와 프로퍼티 정보를 확인하고, 이를 RunningAction와 RunningProperties에 추가한다.
                List<KtsAction> actionList = this.actionInterface.findAction(testRelTestcaseJoinTestcase.getTestcaseGuid());
                List<String> actionGuids = new ArrayList<>();
                for (KtsAction action : actionList) {
                    actionGuids.add(action.getActionGuid());
                }
                Map<String, KtsAction> actionMap = new HashMap<>();
                for (KtsAction action : actionList) {
                    actionMap.put(action.getActionGuid(), action);
                }
                if(actionGuids.stream().count() > 0){
                    List<KtsActionProperty> propertyList = this.propertyInterface.findProperties(new FindPropertiesCommand(actionGuids));

                    List<RunningAction> runningActions = new ArrayList<>();
                    List<RunningProperty> runningProperties = new ArrayList<>();
                    Map<String, String> actionGuidRunningActionGuidMatching = new HashMap<>();
                    //첫번째 액션을 찾는다.
                    KtsAction currentAction = null;
                    for(KtsAction action : actionList){
                        if(action.getIsStart().equals("Y")){
                            currentAction = action;
                            break;
                        }
                    }
                    int actionOrder = 0;
                    while(currentAction != null){
                        //현재 액션을 RUNNING액션에 추가
                        String runningActionGuid = UUID.randomUUID().toString();
                        actionGuidRunningActionGuidMatching.put(currentAction.getActionGuid(), runningActionGuid);

                        RunningAction runningAction = new RunningAction();
                        runningAction.setRunningActionGuid(runningActionGuid);
                        runningAction.setRunningTestcaseGuid(runningTestcaseGuid);
                        runningAction.setActionId(currentAction.getActionId());
                        runningAction.setActionOrder(actionOrder++);
                        runningActions.add(runningAction);

                        if(Strings.isEmpty(currentAction.getNextActionGuid())){
                            //java map은 키가 null인경우 이전값을 그냥 리턴(?!)한다..
                            break;
                        }
                        currentAction = actionMap.get(currentAction.getNextActionGuid());
                    }
                    for (KtsActionProperty property : propertyList) {
                        String runningPropertyGuid = UUID.randomUUID().toString();

                        RunningProperty runningProperty = new RunningProperty();
                        runningProperty.setRunningPropertyGuid(runningPropertyGuid);
                        runningProperty.setRunningActionGuid(actionGuidRunningActionGuidMatching.get(property.getActionGuid()));
                        runningProperty.setRunningPropertyName(property.getPropertyName());
                        runningProperty.setRunningPropertyValue(property.getPropertyValue());

                        runningProperties.add(runningProperty);
                    }

                    System.out.println("add runningActions : " + runningActions);
                    System.out.println("add runningProperties : " + runningProperties);
                    if(runningActions.stream().count() > 0){
                        this.runningActionInterface.addRunningAction(new AddRunningActionCommand(runningActions));

                        if(runningProperties.stream().count() > 0){
                            this.runningPropertyInterface.addRunningProperty(new AddRunningPropertyCommand(runningProperties));
                        }
                    }
                }
            }

            System.out.println("add runningTestcase : " + runningTestcases);
            System.out.println("addUpdate runningTest : " + runningTest);
            this.runningTestcaseInterface.addRunningTestcase(new AddRunningTestcaseCommand(runningTestcases));
            this.runningTestInterface.addOrUpdateRunningTest(runningTest);

            //running에 넣은 값에서 '첫번째'액션의 정보를 클라이언트에 응답한다.
            NextTestInstructionResponse response = new NextTestInstructionResponse();
            RunningAction runningAction = this.findRunningAction(new FindRunningActionCommand(runningTestGuid, 0, 0));
            response.setRunningTest(runningTest);
            if(runningAction != null){
                response.setRunningAction(runningAction);
                response.setRunningProperties(this.runningPropertyInterface.findRunningProperty(runningAction.getRunningActionGuid()));
            }
            return response;
        } catch(Exception e){
            throw e;
        }
    }

    @Override
    @Transactional
    public NextTestInstructionResponse onFinishAction(OnFinishActionCommand command) {
        try {
            NextTestInstructionResponse nextTestInstructionResponse = new NextTestInstructionResponse();

            //step 1. RUNNING_TEST DB의 currentRunningTestcaseOrder, currentRunningActionOrder 정보를 업데이트한다.
            RunningTest runningTest = this.runningTestInterface.findRunningTest(command.getRunningTestGuid());
            List<RunningTestcase> runningTestcases = this.runningTestcaseInterface.findRunningTestcase(runningTest.getRunningTestGuid());
            RunningTestcase runningTestcase = runningTestcases.get(runningTest.getCurrentRunningTestcaseOrder());
            List<RunningAction> runningActions = this.runningActionInterface.findRunningAction(runningTestcase.getRunningTestcaseGuid());
            if(runningTest.getCurrentRunningActionOrder() >= runningActions.stream().count() - 1){
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
            RunningAction nextAction = this.findRunningAction(new FindRunningActionCommand(runningTest.getRunningTestGuid(), runningTest.getCurrentRunningTestcaseOrder(), runningTest.getCurrentRunningActionOrder()));
            nextTestInstructionResponse.setRunningTest(runningTest);
            nextTestInstructionResponse.setRunningAction(nextAction);
            nextTestInstructionResponse.setRunningProperties(this.runningPropertyInterface.findRunningProperty(nextAction.getRunningActionGuid()));
            return nextTestInstructionResponse;
        } catch (Exception e){
            throw e;
        }
    }
    private RunningAction findRunningAction(FindRunningActionCommand command) {
        try {
            //step 1. 현재 runningTest의 currentRunningTestcaseOrder, currentRunningActionOrder 확보
            RunningTest runningTest = this.runningTestInterface.findRunningTest(command.getRunningTestGuid());

            //step 2. 해당 runningTest에 속하는 runningTestcase들을 runningTestcaseOrder로 정렬해서 가져온다음, currentRunningTestcaseOrder에 해당하는 runningTestcase 확보
            List<RunningTestcase> runningTestcases = this.runningTestcaseInterface.findRunningTestcase(runningTest.getRunningTestGuid());
            RunningTestcase runningTestcase = runningTestcases.get(command.getCurrentRunningTestcaseOrder());

            //step 3. 해당 runningTestcase에 속하는 runningAction들을 runningActionOrder로 정렬해서 가져온다음, currentRunningActionOrder에 해당하는 runningAction 확보 및 반환
            List<RunningAction> runningActions = this.runningActionInterface.findRunningAction(runningTestcase.getRunningTestcaseGuid());
            if(runningActions.stream().count() > 0){
                return runningActions.get(command.getCurrentRunningActionOrder());
            }
            return null;
        } catch(Exception e){
            throw e;
        }
    }
}
