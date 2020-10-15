package com.krafton.kts.backend.test_rel_testcase.repository;

import com.krafton.kts.backend.test_rel_testcase.domain.db.TEST_REL_TESTCASE;
import com.krafton.kts.backend.test_rel_testcase.domain.db.TestRelTestcaseDerived;
import com.krafton.kts.backend.test_rel_testcase.domain.command.TestRelTestcaseSaveCommand;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Repo_test_rel_testcase {

    List<TEST_REL_TESTCASE> findTestRelTestcaseByTEST_SEQ(int testSeq);
    void saveTestRelTestcase(TestRelTestcaseSaveCommand command);
    List<TestRelTestcaseDerived> findTestRelTestcaseDerived(int testSeq);
}
