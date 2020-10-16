package com.krafton.kts.backend.action.service;

import com.krafton.kts.backend.action.domain.command.SaveCurrentTestcaseActionsCommand;
import com.krafton.kts.backend.action.domain.command.SavePropertiesCommand;
import com.krafton.kts.backend.action.domain.db.KTS_ACTION;
import com.krafton.kts.backend.action.domain.db.KTS_ACTION_TEMPLATE;
import com.krafton.kts.backend.action.domain.db.KTS_PROPERTY;
import com.krafton.kts.backend.action.domain.db.KTS_PROPERTY_TEMPLATE;

import java.util.List;
import java.util.Map;

public interface ActionService {
    List<KTS_ACTION> findAction(String testcaseGuid);
    List<KTS_PROPERTY> findProperty(String actionGuid);
    List<KTS_PROPERTY_TEMPLATE> getPropertyTemplate(String actionId);
    Map<String, KTS_ACTION_TEMPLATE> getActionTemplate();
    void saveAction(SaveCurrentTestcaseActionsCommand command);
    void saveProperties(SavePropertiesCommand command);
}
