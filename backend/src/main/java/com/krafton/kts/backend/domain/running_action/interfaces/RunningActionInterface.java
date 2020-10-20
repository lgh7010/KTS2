package com.krafton.kts.backend.domain.running_action.interfaces;

import com.krafton.kts.backend.domain.running_action.domain.db.RUNNING_ACTION;

import java.util.List;

public interface RunningActionInterface {
    void addRunningAction(List<RUNNING_ACTION> actions);
    List<RUNNING_ACTION> findRunningAction(String runningTestcaseGuid);
}
