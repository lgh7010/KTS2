package com.krafton.kts.backend.cross;

import com.krafton.kts.backend.domain.action.domain.command.RemoveActionCommand;
import com.krafton.kts.backend.domain.action.domain.command.RemovePropertiesCommand;
import com.krafton.kts.backend.domain.action.domain.command.SaveActionCommand;
import com.krafton.kts.backend.domain.action.domain.command.SaveTestcaseCommand;
import com.krafton.kts.backend.domain.action.service.impl.ActionInterface;
import com.krafton.kts.backend.domain.action.service.impl.PropertyInterface;
import com.krafton.kts.backend.domain.test.domain.command.AddTestCommand;
import com.krafton.kts.backend.domain.test.domain.command.RemoveTestCommand;
import com.krafton.kts.backend.domain.test.service.impl.TestInterface;
import com.krafton.kts.backend.domain.test_rel_testcase.domain.command.RemoveTestRelTestcaseByTestGuidCommand;
import com.krafton.kts.backend.domain.test_rel_testcase.domain.command.RemoveTestRelTestcaseByTestcaseGuidCommand;
import com.krafton.kts.backend.domain.test_rel_testcase.domain.command.SaveTestRelTestcaseCommand;
import com.krafton.kts.backend.domain.test_rel_testcase.service.impl.TestRelTestcaseInterface;
import com.krafton.kts.backend.domain.testcase.domain.command.RemoveTestcaseCommand;
import com.krafton.kts.backend.domain.testcase.service.impl.TestcaseInterface;
import org.springframework.transaction.annotation.Transactional;

public class CrossServiceImpl implements CrossService {

    private ActionInterface actionInterface;
    private PropertyInterface propertyInterface;
    private TestcaseInterface testcaseInterface;
    private TestRelTestcaseInterface testRelTestcaseInterface;
    private TestInterface testInterface;

    public CrossServiceImpl(
            ActionInterface actionInterface,
            PropertyInterface propertyInterface,
            TestcaseInterface testcaseInterface,
            TestRelTestcaseInterface testRelTestcaseInterface,
            TestInterface testInterface
    ){
        this.actionInterface = actionInterface;
        this.propertyInterface = propertyInterface;
        this.testcaseInterface = testcaseInterface;
        this.testRelTestcaseInterface = testRelTestcaseInterface;
        this.testInterface = testInterface;
    }

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
}
