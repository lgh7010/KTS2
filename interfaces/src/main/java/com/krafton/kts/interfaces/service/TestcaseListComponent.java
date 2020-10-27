package com.krafton.kts.interfaces.service;

import com.krafton.kts.domains.entity.KTS_TESTCASE;
import com.krafton.kts.interfaces.repository.testcase.RemoveTestcaseCommand;

import java.util.List;

public interface TestcaseListComponent {
    List<KTS_TESTCASE> findAllTestcase();
    void removeTestcase(RemoveTestcaseCommand command);
}
