package com.krafton.kts.backend.domain.test.service.impl;

import com.krafton.kts.backend.domain.test.domain.command.AddTestCommand;
import com.krafton.kts.backend.domain.test.domain.command.RemoveTestCommand;
import com.krafton.kts.backend.domain.test.domain.db.KTS_TEST;

import java.util.List;

public interface TestInterface {
    KTS_TEST findTest(String testGuid);
    List<KTS_TEST> findAllTest();
    void removeTest(RemoveTestCommand command);
    void addTest(AddTestCommand command);
}
