package com.krafton.kts.interfaces.repository.property;

import com.krafton.kts.domains.entity.KTS_ACTION_PROPERTY;
import lombok.Data;

import java.util.List;

@Data
public class SavePropertiesCommand {
    private List<KTS_ACTION_PROPERTY> properties;
    private List<String> actionGuids;

    public SavePropertiesCommand(List<KTS_ACTION_PROPERTY> properties, List<String> actionGuids){
        this.properties = properties;
        this.actionGuids = actionGuids;
    }
}
