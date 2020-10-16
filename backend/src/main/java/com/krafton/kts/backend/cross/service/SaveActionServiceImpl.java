package com.krafton.kts.backend.cross.service;

import com.krafton.kts.backend.domain.action.domain.command.SaveCurrentTestcaseActionsCommand;
import com.krafton.kts.backend.domain.action.service.interfaces.ActionInterface;
import com.krafton.kts.backend.domain.testcase.service.interfaces.TestcaseInterface;
import org.springframework.transaction.annotation.Transactional;

public class SaveActionServiceImpl implements SaveActionService {

    private ActionInterface actionInterface;
    private TestcaseInterface testcaseInterface;

    public SaveActionServiceImpl(ActionInterface actionInterface, TestcaseInterface testcaseInterface){
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
