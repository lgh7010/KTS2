package com.krafton.kts.interfaces.repository.property;

import com.krafton.kts.domains.entity.KTS_ACTION_PROPERTY;
import com.krafton.kts.domains.entity.KTS_ACTION_PROPERTY_TEMPLATE;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyInterface {
    List<KTS_ACTION_PROPERTY> findProperties(FindPropertiesCommand command);
    List<KTS_ACTION_PROPERTY_TEMPLATE> getPropertyTemplate(String actionId);
    void saveProperties(SavePropertiesCommand command);
    void removeProperties(RemovePropertiesCommand command);
}
