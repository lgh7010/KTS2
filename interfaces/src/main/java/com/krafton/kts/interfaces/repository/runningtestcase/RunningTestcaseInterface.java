package com.krafton.kts.interfaces.repository.runningtestcase;

import com.krafton.kts.domains.entity.RUNNING_TESTCASE;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RunningTestcaseInterface {
    void addRunningTestcase(AddRunningTestcaseCommand command);
    List<RUNNING_TESTCASE> findRunningTestcase(String runningTestGuid);
}
