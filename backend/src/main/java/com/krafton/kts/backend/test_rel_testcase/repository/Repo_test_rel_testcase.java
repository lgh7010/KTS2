package com.krafton.kts.backend.test_rel_testcase.repository;

import com.krafton.kts.backend.test_rel_testcase.domain.TEST_REL_TESTCASE;
import com.krafton.kts.backend.test_rel_testcase.domain.TestRelTestcaseDerived;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Repo_test_rel_testcase {

    List<TEST_REL_TESTCASE> findTestRelTestcaseByTEST_SEQ(int testSeq);
    List<TEST_REL_TESTCASE> findTestRelTestcaseByTESTCASE_GUID(String testcaseGuid);
    void saveTestRelTestcase(List<TEST_REL_TESTCASE> rels, int testSeq, Boolean createNewTest, String testName, String testDescription, List<Integer> removeRelationSeqList);
    List<TestRelTestcaseDerived> findTestRelTestcaseDerived(int testSeq);
}
