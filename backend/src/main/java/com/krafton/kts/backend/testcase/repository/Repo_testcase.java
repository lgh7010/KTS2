package com.krafton.kts.backend.testcase.repository;

import com.krafton.kts.backend.testcase.domain.KTS_TESTCASE;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Repo_testcase {

    Optional<KTS_TESTCASE> findTestcaseByTESTCASE_GUID(String TESTCASE_GUID);
    List<KTS_TESTCASE> findAllTestcase();
    List<KTS_TESTCASE> findTestcasesByTEST_SEQ(int TEST_SEQ);
    void removeTestcase(String TESTCASE_GUID);
    KTS_TESTCASE findTestcase(String TESTCASE_GUID);
    void addTestcase(KTS_TESTCASE tc);
}
