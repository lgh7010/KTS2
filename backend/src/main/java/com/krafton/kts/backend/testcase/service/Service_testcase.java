package com.krafton.kts.backend.testcase.service;

import com.krafton.kts.backend.testcase.domain.KTS_TESTCASE;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Service_testcase {

    List<KTS_TESTCASE> findTestcasesByTEST_SEQ(int TEST_SEQ);
}
