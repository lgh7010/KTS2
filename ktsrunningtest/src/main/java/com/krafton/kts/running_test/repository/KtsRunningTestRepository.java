package com.krafton.kts.running_test.repository;

import com.krafton.kts.domain.running_test.db.RUNNING_TEST;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KtsRunningTestRepository {
    void addOrUpdateRunningTest(RUNNING_TEST runningTest);
    RUNNING_TEST findRunningTest(String runningTestGuid);
    List<RUNNING_TEST> findAllRunningTest();
}
