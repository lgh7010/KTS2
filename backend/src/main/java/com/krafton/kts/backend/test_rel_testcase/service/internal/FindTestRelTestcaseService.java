package com.krafton.kts.backend.test_rel_testcase.service.internal;

import com.krafton.kts.backend.test_rel_testcase.domain.db.TEST_REL_TESTCASE;
import com.krafton.kts.backend.test_rel_testcase.domain.db.TestRelTestcaseDerived;

import java.util.List;

public interface FindTestRelTestcaseService {
    List<TestRelTestcaseDerived> findTestRelTestcaseDerived(int testSeq);
}
