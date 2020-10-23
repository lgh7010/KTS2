package com.krafton.kts.domain.running_property.command;

import com.krafton.kts.domain.running_property.db.RUNNING_PROPERTY;
import lombok.Data;

import java.util.List;

@Data
public class AddRunningPropertyCommand {
    private List<RUNNING_PROPERTY> runningProperties;

    public AddRunningPropertyCommand(List<RUNNING_PROPERTY> runningProperties){
        this.runningProperties = runningProperties;
    }
}
