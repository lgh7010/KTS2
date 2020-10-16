package com.krafton.kts.backend.action.service;

import com.krafton.kts.backend.action.domain.command.SavePropertiesCommand;
import com.krafton.kts.backend.action.domain.db.KTS_ACTION;
import com.krafton.kts.backend.action.domain.db.KTS_ACTION_TEMPLATE;
import com.krafton.kts.backend.action.domain.db.KTS_PROPERTY;
import com.krafton.kts.backend.action.domain.db.KTS_PROPERTY_TEMPLATE;
import com.krafton.kts.backend.action.service.interfaces.*;

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
    public void saveAction(Map<String, KTS_ACTION> map, List<String> removeList) {
        this.actionInterface.saveAction(map, removeList);
    }

    @Override
    public void saveProperties(SavePropertiesCommand command) {
        this.propertyInterface.saveProperties(command);
    }
}
