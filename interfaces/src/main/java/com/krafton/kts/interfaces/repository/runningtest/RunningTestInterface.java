package com.krafton.kts.interfaces.repository.runningtest;


import com.krafton.kts.domains.entity.RUNNING_TEST;

import java.util.List;

public interface RunningTestInterface {
    void addOrUpdateRunningTest(RUNNING_TEST runningTest);
    RUNNING_TEST findRunningTest(String runningTestGuid);
    List<RUNNING_TEST> findAllRunningTest();
}
