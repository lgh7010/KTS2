package com.krafton.kts.backend.testcase.service.interfaces;

import com.krafton.kts.backend.testcase.domain.command.RemoveTestcaseCommand;
import com.krafton.kts.backend.testcase.domain.db.KTS_TESTCASE;

import java.util.List;

public interface TestcaseInterface {
    void addTestcase(KTS_TESTCASE testcase);
    List<KTS_TESTCASE> findAll();
    KTS_TESTCASE findTestcase(String testcaseGuid);
    void removeTestcase(RemoveTestcaseCommand command);
}
