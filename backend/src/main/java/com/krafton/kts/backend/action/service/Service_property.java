package com.krafton.kts.backend.action.service;

import com.krafton.kts.backend.action.domain.db.KTS_PROPERTY;
import com.krafton.kts.backend.action.domain.db.KTS_PROPERTY_TEMPLATE;
import com.krafton.kts.backend.action.domain.command.SavePropertiesCommand;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Service_property {
    List<KTS_PROPERTY> findProperty(String actionGuid);
    List<KTS_PROPERTY_TEMPLATE> findPropertyTemplate(String actionId);
    void saveProperties(SavePropertiesCommand command);
}
