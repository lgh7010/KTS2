package com.krafton.kts.backend.domain.running_action.domain.command;

import com.krafton.kts.backend.domain.running_action.domain.db.RUNNING_ACTION;
import lombok.Data;

import java.util.List;

@Data
public class AddRunningActionCommand {
    private List<RUNNING_ACTION> runningActions;

    public AddRunningActionCommand(List<RUNNING_ACTION> runningActions){
        this.runningActions = runningActions;
    }
}
