package com.krafton.kts.interfaces.repository.runningaction;

import com.krafton.kts.domains.entity.RunningAction;
import lombok.Data;

import java.util.List;

@Data
public class AddRunningActionCommand {
    private List<RunningAction> runningActions;

    public AddRunningActionCommand(List<RunningAction> runningActions){
        this.runningActions = runningActions;
    }
}
