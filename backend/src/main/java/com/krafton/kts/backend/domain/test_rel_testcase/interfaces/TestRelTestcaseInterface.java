package com.krafton.kts.backend.domain.test_rel_testcase.interfaces;

import com.krafton.kts.backend.domain.test_rel_testcase.domain.command.RemoveTestRelTestcaseByTestGuidCommand;
import com.krafton.kts.backend.domain.test_rel_testcase.domain.command.RemoveTestRelTestcaseByTestcaseGuidCommand;
import com.krafton.kts.backend.domain.test_rel_testcase.domain.command.SaveTestRelTestcaseCommand;

public interface TestRelTestcaseInterface {
    void saveTestRelTestcase(SaveTestRelTestcaseCommand command);
    void removeTestRelTestcaseByTestcaseGuid(RemoveTestRelTestcaseByTestcaseGuidCommand command);
    void removeTestRelTestcaseByTestGuid(RemoveTestRelTestcaseByTestGuidCommand command);
}
