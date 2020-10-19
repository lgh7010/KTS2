package com.krafton.kts.backend.cross;

import com.krafton.kts.backend.domain.action.domain.command.SaveTestcaseCommand;
import com.krafton.kts.backend.domain.test.domain.command.RemoveTestCommand;
import com.krafton.kts.backend.domain.test_rel_testcase.domain.command.SaveTestRelTestcaseCommand;
import com.krafton.kts.backend.domain.testcase.domain.command.RemoveTestcaseCommand;

public interface CrossService {
    void saveTestcase(SaveTestcaseCommand command);
    void removeTestcase(RemoveTestcaseCommand command);

    void removeTest(RemoveTestCommand command);

    void saveTestRelTestcase(SaveTestRelTestcaseCommand command);
}
