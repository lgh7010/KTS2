package com.krafton.kts.backend.domain.running_testcase.interfaces;

import com.krafton.kts.backend.domain.running_testcase.domain.db.RUNNING_TESTCASE;

import java.util.List;

public interface RunningTestcaseInterface {
    void addRunningTestcase(List<RUNNING_TESTCASE> runningTestcase);
    List<RUNNING_TESTCASE> findRunningTestcase(String runningTestGuid);
}
