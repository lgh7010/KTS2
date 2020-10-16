package com.krafton.kts.backend.domain.test.service;

import com.krafton.kts.backend.domain.test.domain.db.KTS_TEST;
import com.krafton.kts.backend.domain.test.domain.command.RemoveTestCommand;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TestService {
    List<KTS_TEST> findAllTest();
    KTS_TEST findTest(int testSeq);
    void removeTest(RemoveTestCommand command);
}
