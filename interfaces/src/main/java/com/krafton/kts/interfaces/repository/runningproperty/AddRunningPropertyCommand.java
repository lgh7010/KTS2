package com.krafton.kts.interfaces.repository.runningproperty;

import com.krafton.kts.domains.entity.RunningProperty;
import lombok.Data;

import java.util.List;

@Data
public class AddRunningPropertyCommand {
    private List<RunningProperty> runningProperties;

    public AddRunningPropertyCommand(List<RunningProperty> runningProperties){
        this.runningProperties = runningProperties;
    }
}
