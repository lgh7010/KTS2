package com.krafton.kts.backend.test_rel_testcase.service;

import com.krafton.kts.backend.test_rel_testcase.domain.TEST_REL_TESTCASE;
import com.krafton.kts.backend.test_rel_testcase.domain.TestRelTestcaseDerived;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Service_test_rel_testcase {

    void saveTestRelTestcase(List<TEST_REL_TESTCASE> rels, int testSeq, String testName, String testDescription, List<Integer> removeRelationSeqList);
    List<TestRelTestcaseDerived> findTestRelTestcaseDerived(int testSeq);
}
