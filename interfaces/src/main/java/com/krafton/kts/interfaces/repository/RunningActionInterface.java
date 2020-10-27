package com.krafton.kts.interfaces.repository;

import com.krafton.kts.commands.runningaction.AddRunningActionCommand;
import com.krafton.kts.domains.entity.RUNNING_ACTION;

import java.util.List;

public interface RunningActionInterface {
    void addRunningAction(AddRunningActionCommand command);
    List<RUNNING_ACTION> findRunningAction(String runningTestcaseGuid);
}
