package com.krafton.kts.backend.domain.testcase.service;

import com.krafton.kts.backend.domain.testcase.domain.db.KTS_TESTCASE;
import com.krafton.kts.backend.domain.testcase.domain.command.RemoveTestcaseCommand;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TestcaseService {

    List<KTS_TESTCASE> findAll();
    void removeTestcase(RemoveTestcaseCommand command);
    KTS_TESTCASE findTestcase(String testcaseGuid);
    void addTestcase(KTS_TESTCASE testcase);
}
