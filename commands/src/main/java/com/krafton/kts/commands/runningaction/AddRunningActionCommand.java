package com.krafton.kts.commands.runningaction;

import com.krafton.kts.domains.entity.RUNNING_ACTION;
import lombok.Data;

import java.util.List;

@Data
public class AddRunningActionCommand {
    private List<RUNNING_ACTION> runningActions;

    public AddRunningActionCommand(List<RUNNING_ACTION> runningActions){
        this.runningActions = runningActions;
    }
}
