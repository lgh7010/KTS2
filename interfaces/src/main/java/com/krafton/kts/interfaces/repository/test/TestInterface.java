package com.krafton.kts.interfaces.repository.test;

import com.krafton.kts.domains.entity.KTS_TEST;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestInterface {
    KTS_TEST findTest(String testGuid);
    List<KTS_TEST> findAllTest();
    void removeTest(RemoveTestCommand command);
    void addTest(AddTestCommand command);
}
