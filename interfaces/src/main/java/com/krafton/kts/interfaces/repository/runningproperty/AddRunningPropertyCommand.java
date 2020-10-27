package com.krafton.kts.interfaces.repository.runningproperty;

import com.krafton.kts.domains.entity.RUNNING_PROPERTY;
import lombok.Data;

import java.util.List;

@Data
public class AddRunningPropertyCommand {
    private List<RUNNING_PROPERTY> runningProperties;

    public AddRunningPropertyCommand(List<RUNNING_PROPERTY> runningProperties){
        this.runningProperties = runningProperties;
    }
}
