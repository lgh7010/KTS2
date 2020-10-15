package com.krafton.kts.backend.testcase.service;

import com.krafton.kts.backend.test.domain.KTS_TEST;
import com.krafton.kts.backend.testcase.domain.KTS_TESTCASE;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Service_testcase {

    List<KTS_TESTCASE> findAll();
    List<KTS_TESTCASE> findTestcasesByTEST_SEQ(int TEST_SEQ);
    void removeTestcase(int TESTCASE_SEQ);
    KTS_TESTCASE findTestcase(int TESTCASE_SEQ);
    void updateTestcase(KTS_TESTCASE tc);
}
