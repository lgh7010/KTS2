package com.krafton.kts.interfaces.repository;

import com.krafton.kts.commands.testreltestcase.RemoveTestRelTestcaseByTestGuidCommand;
import com.krafton.kts.commands.testreltestcase.RemoveTestRelTestcaseByTestcaseGuidCommand;
import com.krafton.kts.commands.testreltestcase.SaveTestRelTestcaseCommand;

public interface TestRelTestcaseInterface {
    void saveTestRelTestcase(SaveTestRelTestcaseCommand command);
    void removeTestRelTestcaseByTestcaseGuid(RemoveTestRelTestcaseByTestcaseGuidCommand command);
    void removeTestRelTestcaseByTestGuid(RemoveTestRelTestcaseByTestGuidCommand command);
}
