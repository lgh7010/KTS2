package com.krafton.kts.interfaces.repository.runningaction;

import com.krafton.kts.domains.entity.RunningAction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RunningActionInterface {
    void addRunningAction(AddRunningActionCommand command);
    List<RunningAction> findRunningAction(String runningTestcaseGuid);
}
