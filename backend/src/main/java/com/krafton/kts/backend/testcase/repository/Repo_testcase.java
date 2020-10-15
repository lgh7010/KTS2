package com.krafton.kts.backend.testcase.repository;

import com.krafton.kts.backend.testcase.domain.db.KTS_TESTCASE;
import com.krafton.kts.backend.testcase.domain.command.RemoveTestcaseCommand;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Repo_testcase {

    List<KTS_TESTCASE> findAllTestcase();
    List<KTS_TESTCASE> findTestcasesByTEST_SEQ(int testSeq);
    void removeTestcase(RemoveTestcaseCommand command);
    KTS_TESTCASE findTestcase(String testcaseGuid);
    void addTestcase(KTS_TESTCASE testcase);
}
