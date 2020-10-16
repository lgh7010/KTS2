package com.krafton.kts.backend.domain.action.service;

import com.krafton.kts.backend.domain.action.domain.command.SavePropertiesCommand;
import com.krafton.kts.backend.domain.action.domain.db.KTS_ACTION;
import com.krafton.kts.backend.domain.action.domain.db.KTS_ACTION_TEMPLATE;
import com.krafton.kts.backend.domain.action.domain.db.KTS_ACTION_PROPERTY;
import com.krafton.kts.backend.domain.action.domain.db.KTS_ACTION_PROPERTY_TEMPLATE;

import java.util.List;
import java.util.Map;

public interface ActionService {
    List<KTS_ACTION> findAction(String testcaseGuid);
    List<KTS_ACTION_PROPERTY> findProperty(String actionGuid);
    List<KTS_ACTION_PROPERTY_TEMPLATE> getPropertyTemplate(String actionId);
    Map<String, KTS_ACTION_TEMPLATE> getActionTemplate();

    void saveProperties(SavePropertiesCommand command);
}
