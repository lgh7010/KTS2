package com.krafton.kts.backend.domain.running_test.interfaces;

import com.krafton.kts.backend.domain.running_test.domain.db.RUNNING_TEST;

import java.util.List;

public interface RunningTestInterface {
    void addOrUpdateRunningTest(RUNNING_TEST runningTest);
    RUNNING_TEST findRunningTest(String runningTestGuid);
    List<RUNNING_TEST> findAllRunningTest();
}
