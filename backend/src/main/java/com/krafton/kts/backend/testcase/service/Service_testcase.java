package com.krafton.kts.backend.testcase.service;

import com.krafton.kts.backend.test.domain.KTS_TEST;
import com.krafton.kts.backend.testcase.domain.KTS_TESTCASE;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Service_testcase {

    List<KTS_TESTCASE> findAll();
    List<KTS_TESTCASE> findTestcasesByTEST_SEQ(int TEST_SEQ);
    void removeTestcase(String TESTCASE_GUID);
    KTS_TESTCASE findTestcase(String TESTCASE_GUID);
    void addTestcase(KTS_TESTCASE tc);
}
