package com.krafton.kts.backend.testcase.repository;

import com.krafton.kts.backend.testcase.domain.KTS_TESTCASE;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Repo_testcase {

    List<KTS_TESTCASE> findAllTestcase();
    List<KTS_TESTCASE> findTestcasesByTEST_SEQ(int testSeq);
    void removeTestcase(String testcaseGuid);
    KTS_TESTCASE findTestcase(String testcaseGuid);
    void addTestcase(KTS_TESTCASE testcase);
}
