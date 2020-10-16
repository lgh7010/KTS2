package com.krafton.kts.backend.test_rel_testcase.service.internal;

import com.krafton.kts.backend.test_rel_testcase.domain.command.TestRelTestcaseSaveCommand;

public interface SaveTestRelTestcaseService {
    void saveTestRelTestcase(TestRelTestcaseSaveCommand command);
}
