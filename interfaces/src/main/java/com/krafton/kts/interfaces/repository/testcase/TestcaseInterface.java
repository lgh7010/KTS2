package com.krafton.kts.interfaces.repository.testcase;

import com.krafton.kts.domains.entity.KTS_TESTCASE;

import java.util.List;

public interface TestcaseInterface {
    void addTestcase(KTS_TESTCASE testcase);
    List<KTS_TESTCASE> findAll();
    KTS_TESTCASE findTestcase(String testcaseGuid);
    void removeTestcase(RemoveTestcaseCommand command);
}
