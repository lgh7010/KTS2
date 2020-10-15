package com.krafton.kts.backend.testcase.service;

import com.krafton.kts.backend.test.domain.KTS_TEST;
import com.krafton.kts.backend.testcase.domain.KTS_TESTCASE;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Service_testcase {

    List<KTS_TESTCASE> findAll();
    void removeTestcase(String testcaseGuid);
    KTS_TESTCASE findTestcase(String testcaseGuid);
    void addTestcase(KTS_TESTCASE testcase);
}
