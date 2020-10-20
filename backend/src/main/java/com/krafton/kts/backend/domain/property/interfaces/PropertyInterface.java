package com.krafton.kts.backend.domain.property.interfaces;

import com.krafton.kts.backend.domain.property.domain.command.FindPropertiesCommand;
import com.krafton.kts.backend.domain.property.domain.command.RemovePropertiesCommand;
import com.krafton.kts.backend.domain.property.domain.command.SavePropertiesCommand;
import com.krafton.kts.backend.domain.property.domain.db.KTS_ACTION_PROPERTY;
import com.krafton.kts.backend.domain.property.domain.db.KTS_ACTION_PROPERTY_TEMPLATE;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface PropertyInterface {
//    List<KTS_ACTION_PROPERTY> findProperty(String actionGuid);
    List<KTS_ACTION_PROPERTY> findProperties(FindPropertiesCommand command);
    List<KTS_ACTION_PROPERTY_TEMPLATE> getPropertyTemplate(String actionId);
    void saveProperties(SavePropertiesCommand command);
    void removeProperties(RemovePropertiesCommand command);
}
