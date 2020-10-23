package com.krafton.kts.running_testcase.repository;

import com.krafton.kts.domain.running_testcase.command.AddRunningTestcaseCommand;
import com.krafton.kts.domain.running_testcase.db.RUNNING_TESTCASE;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KtsRunningTestcaseRepository {
    void addRunningTestcase(AddRunningTestcaseCommand command);
    List<RUNNING_TESTCASE> findRunningTestcase(String runningTestGuid);
}
