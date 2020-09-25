package com.krafton.kts.module_testcaselist.service;

import com.krafton.kts.module_repository.testinfo.domain.TEST_REL_TESTCASE;
import com.krafton.kts.module_testcaselist.domain.KTS_TESTCASE;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TestcaseListService {

    List<KTS_TESTCASE> findTestcasesByTEST_SEQ(int TEST_SEQ);
    List<TEST_REL_TESTCASE> findTestRelTestcaseByTEST_SEQ(int TEST_SEQ);
}
