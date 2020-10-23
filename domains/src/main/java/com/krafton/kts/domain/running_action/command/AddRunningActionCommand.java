package com.krafton.kts.domain.running_action.command;

import com.krafton.kts.domain.running_action.db.RUNNING_ACTION;
import lombok.Data;

import java.util.List;

@Data
public class AddRunningActionCommand {
    private List<RUNNING_ACTION> runningActions;

    public AddRunningActionCommand(List<RUNNING_ACTION> runningActions){
        this.runningActions = runningActions;
    }
}
