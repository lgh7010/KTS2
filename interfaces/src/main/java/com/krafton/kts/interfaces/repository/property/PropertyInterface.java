package com.krafton.kts.interfaces.repository.property;

import com.krafton.kts.domains.entity.KtsActionProperty;
import com.krafton.kts.domains.entity.KtsActionPropertyTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PropertyInterface {
    List<KtsActionProperty> findProperties(FindPropertiesCommand command);
    List<KtsActionPropertyTemplate> getPropertyTemplate(String actionId);
    void saveProperties(SavePropertiesCommand command);
    void removeProperties(RemovePropertiesCommand command);
}
