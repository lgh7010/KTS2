package com.krafton.kts.backend.action.service;

import com.krafton.kts.backend.action.domain.command.SaveCurrentTestcaseActionsCommand;
import com.krafton.kts.backend.action.domain.command.SavePropertiesCommand;
import com.krafton.kts.backend.action.domain.db.KTS_ACTION;
import com.krafton.kts.backend.action.domain.db.KTS_ACTION_TEMPLATE;
import com.krafton.kts.backend.action.domain.db.KTS_PROPERTY;
import com.krafton.kts.backend.action.domain.db.KTS_PROPERTY_TEMPLATE;
import com.krafton.kts.backend.action.service.interfaces.*;
import com.krafton.kts.backend.testcase.service.interfaces.TestcaseInterface;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public class ActionServiceImpl implements ActionService {

    private ActionInterface actionInterface;
    private TestcaseInterface testcaseInterface;
    private PropertyInterface propertyInterface;

    public ActionServiceImpl(
            ActionInterface actionInterface,
            TestcaseInterface testcaseInterface,
            PropertyInterface findPropertyService
    ){
        this.actionInterface = actionInterface;
        this.testcaseInterface = testcaseInterface;
        this.propertyInterface = findPropertyService;
    }

    @Override
    public List<KTS_ACTION> findAction(String testcaseGuid) {
        return this.actionInterface.findAction(testcaseGuid);
    }

    @Override
    public List<KTS_PROPERTY> findProperty(String actionGuid) {
        return this.propertyInterface.findProperty(actionGuid);
    }

    @Override
    public List<KTS_PROPERTY_TEMPLATE> getPropertyTemplate(String actionId) {
        return this.propertyInterface.getPropertyTemplate(actionId);
    }

    @Override
    public Map<String, KTS_ACTION_TEMPLATE> getActionTemplate() {
        return this.actionInterface.getActionTemplate();
    }

    @Override
    @Transactional
    public void saveAction(SaveCurrentTestcaseActionsCommand command) {
        this.actionInterface.saveAction(command.getCurrentTestcaseActions(), command.getRemoveActionGuidList());
        this.testcaseInterface.addTestcase(command.getTestcase());
    }

    @Override
    public void saveProperties(SavePropertiesCommand command) {
        this.propertyInterface.saveProperties(command);
    }
}
