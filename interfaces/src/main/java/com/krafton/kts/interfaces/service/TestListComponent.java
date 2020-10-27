package com.krafton.kts.interfaces.service;

import com.krafton.kts.domains.entity.KTS_TEST;
import com.krafton.kts.interfaces.repository.test.RemoveTestCommand;

import java.util.List;

public interface TestListComponent {
    List<KTS_TEST> findAllTest();
    void removeTest(RemoveTestCommand command);
}
