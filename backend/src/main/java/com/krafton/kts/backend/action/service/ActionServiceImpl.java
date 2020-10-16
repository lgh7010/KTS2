package com.krafton.kts.backend.action.service;

import com.krafton.kts.backend.action.domain.command.SavePropertiesCommand;
import com.krafton.kts.backend.action.domain.db.KTS_ACTION;
import com.krafton.kts.backend.action.domain.db.KTS_ACTION_TEMPLATE;
import com.krafton.kts.backend.action.domain.db.KTS_PROPERTY;
import com.krafton.kts.backend.action.domain.db.KTS_PROPERTY_TEMPLATE;
import com.krafton.kts.backend.action.service.internal.*;

import java.util.List;
import java.util.Map;

public class ActionServiceImpl implements ActionService {

    private FindActionService findActionService;
    private FindPropertyService findPropertyService;
    private GetPropertyTemplateService getPropertyTemplateService;
    private GetActionTemplateService getActionTemplateService;
    private SaveActionService saveActionService;
    private SavePropertyService savePropertyService;

    public ActionServiceImpl(
            FindActionService findActionService,
            FindPropertyService findPropertyService,
            GetPropertyTemplateService getPropertyTemplateService,
            GetActionTemplateService getActionTemplateService,
            SaveActionService saveActionService,
            SavePropertyService savePropertyService
    ){
        this.findActionService = findActionService;
        this.findPropertyService = findPropertyService;
        this.getPropertyTemplateService = getPropertyTemplateService;
        this.getActionTemplateService = getActionTemplateService;
        this.saveActionService = saveActionService;
        this.savePropertyService = savePropertyService;
    }

    @Override
    public List<KTS_ACTION> findAction(String testcaseGuid) {
        return this.findActionService.findAction(testcaseGuid);
    }

    @Override
    public List<KTS_PROPERTY> findProperty(String actionGuid) {
        return this.findPropertyService.findProperty(actionGuid);
    }

    @Override
    public List<KTS_PROPERTY_TEMPLATE> getPropertyTemplate(String actionId) {
        return this.getPropertyTemplateService.getPropertyTemplate(actionId);
    }

    @Override
    public Map<String, KTS_ACTION_TEMPLATE> getActionTemplate() {
        return this.getActionTemplateService.getActionTemplate();
    }

    @Override
    public void saveAction(Map<String, KTS_ACTION> map, List<String> removeList) {
        this.saveActionService.saveAction(map, removeList);
    }

    @Override
    public void saveProperties(SavePropertiesCommand command) {
        this.savePropertyService.saveProperties(command);
    }
}
