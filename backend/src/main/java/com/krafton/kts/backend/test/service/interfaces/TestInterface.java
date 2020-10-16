package com.krafton.kts.backend.test.service.interfaces;

import com.krafton.kts.backend.test.domain.command.RemoveTestCommand;
import com.krafton.kts.backend.test.domain.db.KTS_TEST;

import java.util.List;

public interface TestInterface {
    KTS_TEST findTest(int testSeq);
    List<KTS_TEST> findAllTest();
    void RemoveTest(RemoveTestCommand command);
}
