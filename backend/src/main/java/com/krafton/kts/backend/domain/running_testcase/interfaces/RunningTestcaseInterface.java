package com.krafton.kts.backend.domain.running_testcase.interfaces;

import com.krafton.kts.backend.domain.running_testcase.domain.command.AddRunningTestcaseCommand;
import com.krafton.kts.backend.domain.running_testcase.domain.db.RUNNING_TESTCASE;

import java.util.List;

public interface RunningTestcaseInterface {
    void addRunningTestcase(AddRunningTestcaseCommand command);
    List<RUNNING_TESTCASE> findRunningTestcase(String runningTestGuid);
}
