package com.krafton.kts.running_action.repository;

import com.krafton.kts.domain.running_action.command.AddRunningActionCommand;
import com.krafton.kts.domain.running_action.db.RUNNING_ACTION;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KtsRunningActionRepository {
    void addRunningAction(AddRunningActionCommand command);
    List<RUNNING_ACTION> findRunningAction(String runningTestcaseGuid);
}
