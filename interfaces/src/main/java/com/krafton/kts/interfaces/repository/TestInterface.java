package com.krafton.kts.interfaces.repository;

import com.krafton.kts.commands.test.AddTestCommand;
import com.krafton.kts.commands.test.RemoveTestCommand;
import com.krafton.kts.domains.entity.KTS_TEST;

import java.util.List;

public interface TestInterface {
    KTS_TEST findTest(String testGuid);
    List<KTS_TEST> findAllTest();
    void removeTest(RemoveTestCommand command);
    void addTest(AddTestCommand command);
}
