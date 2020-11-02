package com.krafton.kts.interfaces.repository.runningtest;


import com.krafton.kts.domains.entity.RunningTest;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface RunningTestInterface {
    void addOrUpdateRunningTest(RunningTest runningTest);
    RunningTest findRunningTest(String runningTestGuid);
    List<RunningTest> findAllRunningTest();
}
