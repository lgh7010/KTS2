package com.krafton.kts.backend.domain.property.domain.command;

import com.krafton.kts.backend.domain.property.domain.db.KTS_ACTION_PROPERTY;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SavePropertiesCommand {
    private List<KTS_ACTION_PROPERTY> properties;
    private List<String> actionGuids;

    public SavePropertiesCommand(List<KTS_ACTION_PROPERTY> properties, List<String> actionGuids){
        this.properties = properties;
        this.actionGuids = actionGuids;
    }
}
