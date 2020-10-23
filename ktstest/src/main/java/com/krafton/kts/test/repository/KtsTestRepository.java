package com.krafton.kts.test.repository;

import com.krafton.kts.domain.test.command.AddTestCommand;
import com.krafton.kts.domain.test.command.RemoveTestCommand;
import com.krafton.kts.domain.test.db.KTS_TEST;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KtsTestRepository {
    KTS_TEST findTest(String testGuid);
    List<KTS_TEST> findAllTest();
    void removeTest(RemoveTestCommand command);
    void addTest(AddTestCommand command);
}
