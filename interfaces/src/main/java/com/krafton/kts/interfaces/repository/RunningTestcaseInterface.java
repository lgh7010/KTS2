package com.krafton.kts.interfaces.repository;

import com.krafton.kts.commands.runningtestcase.AddRunningTestcaseCommand;
import com.krafton.kts.domains.entity.RUNNING_TESTCASE;

import java.util.List;

public interface RunningTestcaseInterface {
    void addRunningTestcase(AddRunningTestcaseCommand command);
    List<RUNNING_TESTCASE> findRunningTestcase(String runningTestGuid);
}
