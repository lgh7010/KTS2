package com.krafton.kts.backend.domain.test_rel_testcase.service.impl;

import com.krafton.kts.backend.domain.test_rel_testcase.domain.command.TestRelTestcaseSaveCommand;
import com.krafton.kts.backend.domain.test_rel_testcase.domain.db.TestRelTestcaseDerived;

import java.util.List;

public interface TestRelTestcaseInterface {
    List<TestRelTestcaseDerived> findTestRelTestcaseDerived(String testGuid);
    void saveTestRelTestcase(TestRelTestcaseSaveCommand command);
}
