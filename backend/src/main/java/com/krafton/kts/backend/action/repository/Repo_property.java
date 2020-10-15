package com.krafton.kts.backend.action.repository;

import com.krafton.kts.backend.action.domain.db.KTS_PROPERTY;
import com.krafton.kts.backend.action.domain.db.KTS_PROPERTY_TEMPLATE;
import com.krafton.kts.backend.action.domain.command.SavePropertiesCommand;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Repo_property {
    List<KTS_PROPERTY> findProperty(String actionGuid);
    List<KTS_PROPERTY_TEMPLATE> findPropertyTemplate(String actionId);
    void saveProperties(SavePropertiesCommand command);
}
