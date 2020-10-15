package com.krafton.kts.backend.testcase.service;

import com.krafton.kts.backend.testcase.domain.db.KTS_TESTCASE;
import com.krafton.kts.backend.testcase.domain.command.RemoveTestcaseCommand;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Service_testcase {

    List<KTS_TESTCASE> findAll();
    void removeTestcase(RemoveTestcaseCommand command);
    KTS_TESTCASE findTestcase(String testcaseGuid);
    void addTestcase(KTS_TESTCASE testcase);
}
