package com.krafton.kts.backend.cross.service;

import com.krafton.kts.backend.domain.action.domain.command.SaveCurrentTestcaseActionsCommand;
import com.krafton.kts.backend.domain.action.service.impl.ActionInterface;
import com.krafton.kts.backend.domain.testcase.service.impl.TestcaseInterface;
import org.springframework.transaction.annotation.Transactional;

public class CrossDomainInterfaceImpl implements CrossDomainInterface {

    private ActionInterface actionInterface;
    private TestcaseInterface testcaseInterface;

    public CrossDomainInterfaceImpl(ActionInterface actionInterface, TestcaseInterface testcaseInterface){
        this.actionInterface = actionInterface;
        this.testcaseInterface = testcaseInterface;
    }

    @Override
    @Transactional
    public void saveAction(SaveCurrentTestcaseActionsCommand command) {
        this.actionInterface.saveAction(command.getCurrentTestcaseActions(), command.getRemoveActionGuidList());
        this.testcaseInterface.addTestcase(command.getTestcase());
    }
}
