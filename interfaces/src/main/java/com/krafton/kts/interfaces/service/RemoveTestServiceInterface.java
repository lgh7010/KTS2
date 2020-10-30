package com.krafton.kts.interfaces.service;

import com.krafton.kts.interfaces.repository.test.RemoveTestCommand;

public interface RemoveTestServiceInterface {
    void removeTest(RemoveTestCommand command);
}
