package com.krafton.kts.backend.testcase.service.internal;

import com.krafton.kts.backend.testcase.domain.command.RemoveTestcaseCommand;

public interface RemoveTestcaseService {
    void removeTestcase(RemoveTestcaseCommand command);
}
