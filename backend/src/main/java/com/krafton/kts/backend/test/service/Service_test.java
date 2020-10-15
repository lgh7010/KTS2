package com.krafton.kts.backend.test.service;

import com.krafton.kts.backend.test.domain.db.KTS_TEST;
import com.krafton.kts.backend.test.domain.command.RemoveTestCommand;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Service_test {

    List<KTS_TEST> findAll();
    KTS_TEST find(int TEST_SEQ);
    void removeTest(RemoveTestCommand command);

}
