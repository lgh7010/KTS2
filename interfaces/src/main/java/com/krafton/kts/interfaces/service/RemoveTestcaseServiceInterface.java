package com.krafton.kts.interfaces.service;

import com.krafton.kts.interfaces.repository.testcase.RemoveTestcaseCommand;

public interface RemoveTestcaseServiceInterface {
    void removeTestcase(RemoveTestcaseCommand command);
}
