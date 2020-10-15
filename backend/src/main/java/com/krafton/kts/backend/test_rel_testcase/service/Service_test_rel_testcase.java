package com.krafton.kts.backend.test_rel_testcase.service;

import com.krafton.kts.backend.test_rel_testcase.domain.TEST_REL_TESTCASE;
import com.krafton.kts.backend.test_rel_testcase.domain.TestRelTestcaseDerived;
import com.krafton.kts.backend.test_rel_testcase.domain.TestRelTestcaseSaveCommand;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Service_test_rel_testcase {

    void saveTestRelTestcase(TestRelTestcaseSaveCommand command);
    List<TestRelTestcaseDerived> findTestRelTestcaseDerived(int testSeq);
}
