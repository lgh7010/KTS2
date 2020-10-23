package com.krafton.kts.property.repository;

import com.krafton.kts.domain.property.command.FindPropertiesCommand;
import com.krafton.kts.domain.property.command.RemovePropertiesCommand;
import com.krafton.kts.domain.property.command.SavePropertiesCommand;
import com.krafton.kts.domain.property.db.KTS_ACTION_PROPERTY;
import com.krafton.kts.domain.property.db.KTS_ACTION_PROPERTY_TEMPLATE;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KtsPropertyRepository {
    List<KTS_ACTION_PROPERTY> findProperties(FindPropertiesCommand command);
    List<KTS_ACTION_PROPERTY_TEMPLATE> getPropertyTemplate(String actionId);
    void saveProperties(SavePropertiesCommand command);
    void removeProperties(RemovePropertiesCommand command);
}
