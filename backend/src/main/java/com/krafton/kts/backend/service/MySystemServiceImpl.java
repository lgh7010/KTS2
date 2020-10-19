package com.krafton.kts.backend.service;

import com.krafton.kts.backend.domain.action.domain.command.*;
import com.krafton.kts.backend.domain.action.domain.db.KTS_ACTION;
import com.krafton.kts.backend.domain.property.domain.command.RemovePropertiesCommand;
import com.krafton.kts.backend.domain.property.domain.command.SavePropertiesCommand;
import com.krafton.kts.backend.domain.property.domain.db.KTS_ACTION_PROPERTY;
import com.krafton.kts.backend.domain.property.domain.db.KTS_ACTION_PROPERTY_TEMPLATE;
import com.krafton.kts.backend.domain.action.domain.db.KTS_ACTION_TEMPLATE;
import com.krafton.kts.backend.domain.action.interfaces.ActionInterface;
import com.krafton.kts.backend.domain.property.interfaces.PropertyInterface;
import com.krafton.kts.backend.domain.test.domain.command.AddTestCommand;
import com.krafton.kts.backend.domain.test.domain.command.RemoveTestCommand;
import com.krafton.kts.backend.domain.test.domain.db.KTS_TEST;
import com.krafton.kts.backend.domain.test.interfaces.TestInterface;
import com.krafton.kts.backend.domain.test_rel_testcase.domain.command.RemoveTestRelTestcaseByTestGuidCommand;
import com.krafton.kts.backend.domain.test_rel_testcase.domain.command.RemoveTestRelTestcaseByTestcaseGuidCommand;
import com.krafton.kts.backend.domain.test_rel_testcase.domain.command.SaveTestRelTestcaseCommand;
import com.krafton.kts.backend.domain.test_rel_testcase.domain.db.TestRelTestcaseDerived;
import com.krafton.kts.backend.domain.test_rel_testcase.interfaces.TestRelTestcaseInterface;
import com.krafton.kts.backend.domain.testcase.domain.command.RemoveTestcaseCommand;
import com.krafton.kts.backend.domain.testcase.domain.db.KTS_TESTCASE;
import com.krafton.kts.backend.domain.testcase.interfaces.TestcaseInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class MySystemServiceImpl implements MySystemService {

    private final ActionInterface actionInterface;
    private final PropertyInterface propertyInterface;
    private final TestcaseInterface testcaseInterface;
    private final TestRelTestcaseInterface testRelTestcaseInterface;
    private final TestInterface testInterface;

    //transactional
    @Override
    @Transactional
    public void saveTestcase(SaveTestcaseCommand command) {
        if(command.getCurrentTestcaseActions().size() > 0){
            this.actionInterface.saveAction(new SaveActionCommand(command.getCurrentTestcaseActions(), command.getRemoveActionGuidList()));
        }
        this.testcaseInterface.addTestcase(command.getTestcase());
    }

    @Override
    @Transactional
    public void removeTestcase(RemoveTestcaseCommand command) {
        this.testcaseInterface.removeTestcase(command);
        this.actionInterface.removeAction(new RemoveActionCommand(command.getTestcaseGuid()));
        this.testRelTestcaseInterface.removeTestRelTestcase(new RemoveTestRelTestcaseByTestcaseGuidCommand(command.getTestcaseGuid()));
        this.propertyInterface.removeProperties(new RemovePropertiesCommand(command.getTestcaseGuid()));
    }

    @Override
    @Transactional
    public void removeTest(RemoveTestCommand command) {
        this.testInterface.removeTest(command);
        this.testRelTestcaseInterface.removeTestRelTestcase(new RemoveTestRelTestcaseByTestGuidCommand(command.getTestGuid()));
    }

    @Override
    @Transactional
    public void saveTestRelTestcase(SaveTestRelTestcaseCommand command) {
        this.testRelTestcaseInterface.saveTestRelTestcase(command);
        this.testInterface.addTest(new AddTestCommand(command.getTestGuid(), command.getTestName(), command.getTestDescription()));
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
    public List<KTS_ACTION_PROPERTY> findProperty(String actionGuid) {
        return this.propertyInterface.findProperty(actionGuid);
    }
    @Override
    public List<KTS_ACTION_PROPERTY_TEMPLATE> getPropertyTemplate(String actionId) {
        return this.propertyInterface.getPropertyTemplate(actionId);
    }
    @Override
    public void saveProperties(SavePropertiesCommand command) {
        this.propertyInterface.saveProperties(command);
        this.actionInterface.updateActionId(new UpdateActionIdCommand(command.getActionGuid(), command.getActionId()));
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
    public List<TestRelTestcaseDerived> findTestRelTestcaseDerived(String testGuid) {
        return this.testRelTestcaseInterface.findTestRelTestcaseDerived(testGuid);
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
