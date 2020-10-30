package com.krafton.kts.interfaces.repository.runningtest;


import com.krafton.kts.domains.entity.RUNNING_TEST;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RunningTestInterface {
    void addOrUpdateRunningTest(RUNNING_TEST runningTest);
    RUNNING_TEST findRunningTest(String runningTestGuid);
    List<RUNNING_TEST> findAllRunningTest();
}
