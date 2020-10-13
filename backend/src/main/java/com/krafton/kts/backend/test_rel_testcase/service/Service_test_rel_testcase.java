package com.krafton.kts.backend.test_rel_testcase.service;

import com.krafton.kts.backend.test_rel_testcase.domain.TEST_REL_TESTCASE;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Service_test_rel_testcase {

    List<TEST_REL_TESTCASE> findTestRelTestcaseByTEST_SEQ(int TEST_SEQ);
    void saveTestRelTestcase(List<TEST_REL_TESTCASE> rels, int TEST_SEQ, String testName, String testDescription, List<Integer> removeTestcaseSeqList);
}
