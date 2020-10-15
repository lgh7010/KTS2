package com.krafton.kts.backend.test.service.internal;

import com.krafton.kts.backend.test.domain.command.RemoveTestCommand;

public interface RemoveTestService {

    void RemoveTest(RemoveTestCommand command);
}
