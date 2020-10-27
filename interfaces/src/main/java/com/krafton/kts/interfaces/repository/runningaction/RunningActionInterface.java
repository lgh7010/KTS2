package com.krafton.kts.interfaces.repository.runningaction;

import com.krafton.kts.domains.entity.RUNNING_ACTION;

import java.util.List;

public interface RunningActionInterface {
    void addRunningAction(AddRunningActionCommand command);
    List<RUNNING_ACTION> findRunningAction(String runningTestcaseGuid);
}
