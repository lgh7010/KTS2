package com.krafton.kts.interfaces.repository;

import com.krafton.kts.commands.property.FindPropertiesCommand;
import com.krafton.kts.commands.property.RemovePropertiesCommand;
import com.krafton.kts.commands.property.SavePropertiesCommand;
import com.krafton.kts.domains.entity.KTS_ACTION_PROPERTY;
import com.krafton.kts.domains.entity.KTS_ACTION_PROPERTY_TEMPLATE;

import java.util.List;

public interface PropertyInterface {
    List<KTS_ACTION_PROPERTY> findProperties(FindPropertiesCommand command);
    List<KTS_ACTION_PROPERTY_TEMPLATE> getPropertyTemplate(String actionId);
    void saveProperties(SavePropertiesCommand command);
    void removeProperties(RemovePropertiesCommand command);
}
