package com.krafton.kts.interfaces.repository.runningtestcase;

import com.krafton.kts.domains.entity.RUNNING_TESTCASE;

import java.util.List;

public interface RunningTestcaseInterface {
    void addRunningTestcase(AddRunningTestcaseCommand command);
    List<RUNNING_TESTCASE> findRunningTestcase(String runningTestGuid);
}
