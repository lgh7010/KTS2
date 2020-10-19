package com.krafton.kts.backend.domain.testcase.interfaces;

import com.krafton.kts.backend.domain.testcase.domain.command.RemoveTestcaseCommand;
import com.krafton.kts.backend.domain.testcase.domain.db.KTS_TESTCASE;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TestcaseInterface {
    void addTestcase(KTS_TESTCASE testcase);
    List<KTS_TESTCASE> findAll();
    KTS_TESTCASE findTestcase(String testcaseGuid);
    void removeTestcase(RemoveTestcaseCommand command);
}
