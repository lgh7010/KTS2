package com.krafton.kts.interfaces.repository.runningtestcase;

import com.krafton.kts.domains.entity.RunningTestcase;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface RunningTestcaseInterface {
    void addRunningTestcase(AddRunningTestcaseCommand command);
    List<RunningTestcase> findRunningTestcase(String runningTestGuid);
}
