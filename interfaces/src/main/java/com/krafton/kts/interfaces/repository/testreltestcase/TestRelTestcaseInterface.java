package com.krafton.kts.interfaces.repository.testreltestcase;

import org.springframework.stereotype.Repository;

public interface TestRelTestcaseInterface {
    void saveTestRelTestcase(SaveTestRelTestcaseCommand command);
    void removeTestRelTestcaseByTestcaseGuid(RemoveTestRelTestcaseByTestcaseGuidCommand command);
    void removeTestRelTestcaseByTestGuid(RemoveTestRelTestcaseByTestGuidCommand command);
}
