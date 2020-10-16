package com.krafton.kts.backend.domain.action.service.impl;

import com.krafton.kts.backend.domain.action.domain.command.SavePropertiesCommand;
import com.krafton.kts.backend.domain.action.domain.db.KTS_ACTION_PROPERTY;
import com.krafton.kts.backend.domain.action.domain.db.KTS_ACTION_PROPERTY_TEMPLATE;

import java.util.List;

public interface PropertyInterface {
    List<KTS_ACTION_PROPERTY> findProperty(String actionGuid);
    List<KTS_ACTION_PROPERTY_TEMPLATE> getPropertyTemplate(String actionId);
    void saveProperties(SavePropertiesCommand command);
}
