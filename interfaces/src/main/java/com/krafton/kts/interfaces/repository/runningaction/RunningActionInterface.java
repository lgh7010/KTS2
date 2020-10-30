package com.krafton.kts.interfaces.repository.runningaction;

import com.krafton.kts.domains.entity.RUNNING_ACTION;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RunningActionInterface {
    void addRunningAction(AddRunningActionCommand command);
    List<RUNNING_ACTION> findRunningAction(String runningTestcaseGuid);
}
