package com.krafton.kts.backend.domain.property.domain.command;

import com.krafton.kts.backend.domain.property.domain.db.KTS_ACTION_PROPERTY;
import lombok.Data;

import java.util.List;

@Data
public class SavePropertiesCommand {
    private String actionGuid;
    private String actionId;
    private List<KTS_ACTION_PROPERTY> properties;
}
