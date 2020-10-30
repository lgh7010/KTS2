package com.krafton.kts.interfaces.repository.test;

import com.krafton.kts.domains.entity.KtsTest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestInterface {
    KtsTest findTest(String testGuid);
    List<KtsTest> findAllTest();
    void removeTest(RemoveTestCommand command);
    void addTest(AddTestCommand command);
}
