package com.krafton.kts.backend.test_rel_testcase.repository;

import com.krafton.kts.backend.test_rel_testcase.domain.TEST_REL_TESTCASE;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Repo_test_rel_testcase {

    List<TEST_REL_TESTCASE> findTestRelTestcaseByTEST_SEQ(int TEST_SEQ);
    List<TEST_REL_TESTCASE> findTestRelTestcaseByTESTCASE_SEQ(int TESTCASE_SEQ);

}
