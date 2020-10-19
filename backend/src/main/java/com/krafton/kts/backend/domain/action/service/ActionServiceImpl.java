package com.krafton.kts.backend.domain.action.service;

import com.krafton.kts.backend.domain.action.domain.command.SavePropertiesCommand;
import com.krafton.kts.backend.domain.action.domain.command.UpdateActionIdCommand;
import com.krafton.kts.backend.domain.action.domain.db.KTS_ACTION;
import com.krafton.kts.backend.domain.action.domain.db.KTS_ACTION_TEMPLATE;
import com.krafton.kts.backend.domain.action.domain.db.KTS_ACTION_PROPERTY;
import com.krafton.kts.backend.domain.action.domain.db.KTS_ACTION_PROPERTY_TEMPLATE;
import com.krafton.kts.backend.domain.action.service.impl.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionServiceImpl implements ActionService {

    private ActionInterface actionInterface;
    private PropertyInterface propertyInterface;

    public ActionServiceImpl(
            ActionInterface actionInterface,
            PropertyInterface findPropertyService
    ){
        this.actionInterface = actionInterface;
        this.propertyInterface = findPropertyService;
    }

    @Override
    public List<KTS_ACTION> findAction(String testcaseGuid) {
        return this.actionInterface.findAction(testcaseGuid);
    }

    @Override
    public List<KTS_ACTION_PROPERTY> findProperty(String actionGuid) {
        return this.propertyInterface.findProperty(actionGuid);
    }

    @Override
    public List<KTS_ACTION_PROPERTY_TEMPLATE> getPropertyTemplate(String actionId) {
        return this.propertyInterface.getPropertyTemplate(actionId);
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

    @Override
    @Transactional
    public void saveProperties(SavePropertiesCommand command) {
        this.propertyInterface.saveProperties(command);
        this.actionInterface.updateActionId(new UpdateActionIdCommand(command.getActionGuid(), command.getActionId()));
    }
}
