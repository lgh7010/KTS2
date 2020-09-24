package com.krafton.kts.module_testcaselist.service;

import com.krafton.kts.module_testcaselist.domain.KTS_TESTCASE;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TestcaseListService {

    List<KTS_TESTCASE> findTestcaseByTEST_SEQ(int TEST_SEQ);
}
