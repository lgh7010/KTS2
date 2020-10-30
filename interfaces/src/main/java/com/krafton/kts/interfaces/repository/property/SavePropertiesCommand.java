package com.krafton.kts.interfaces.repository.property;

import com.krafton.kts.domains.entity.KtsActionProperty;
import lombok.Data;

import java.util.List;

@Data
public class SavePropertiesCommand {
    private List<KtsActionProperty> properties;
    private List<String> actionGuids;

    public SavePropertiesCommand(List<KtsActionProperty> properties, List<String> actionGuids){
        this.properties = properties;
        this.actionGuids = actionGuids;
    }
}
