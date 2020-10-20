package com.krafton.kts.backend.domain.running_test.interfaces;

import com.krafton.kts.backend.domain.running_test.domain.db.RUNNING_TEST;

public interface RunningTestInterface {
    void addRunningTest(RUNNING_TEST runningTest);
    RUNNING_TEST findRunningTest(String runningTestGuid);
}
