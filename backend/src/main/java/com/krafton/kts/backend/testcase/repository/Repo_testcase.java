package com.krafton.kts.backend.testcase.repository;

import com.krafton.kts.backend.testcase.domain.KTS_TESTCASE;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Repo_testcase {

    KTS_TESTCASE saveTestcase(KTS_TESTCASE testcase);
    Optional<KTS_TESTCASE> findTestcaseByTESTCASE_SEQ(int TESTCASE_SEQ);
    Optional<KTS_TESTCASE> findTestcaseByNAME(String NAME);
    List<KTS_TESTCASE> findAllTestcase();
    List<KTS_TESTCASE> findTestcasesByTEST_SEQ(int TEST_SEQ);
}
