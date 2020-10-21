package com.krafton.kts.backend.domain.running_property.domain.command;

import com.krafton.kts.backend.domain.running_property.domain.db.RUNNING_PROPERTY;
import lombok.Data;

import java.util.List;

@Data
public class AddRunningPropertyCommand {
    private List<RUNNING_PROPERTY> runningProperties;

    public AddRunningPropertyCommand(List<RUNNING_PROPERTY> runningProperties){
        this.runningProperties = runningProperties;
    }
}
