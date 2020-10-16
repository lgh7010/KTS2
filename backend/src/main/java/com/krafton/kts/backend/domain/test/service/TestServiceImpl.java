package com.krafton.kts.backend.domain.test.service;

import com.krafton.kts.backend.domain.test.domain.command.RemoveTestCommand;
import com.krafton.kts.backend.domain.test.domain.db.KTS_TEST;
import com.krafton.kts.backend.domain.test.service.impl.TestInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestServiceImpl implements TestService {

    private TestInterface testInterface;

    @Autowired
    public TestServiceImpl(TestInterface findTestService){
        this.testInterface = findTestService;
    }

    @Override
    public List<KTS_TEST> findAllTest() {
        return this.testInterface.findAllTest();
    }

    @Override
    public KTS_TEST findTest(String testGuid) {
        return this.testInterface.findTest(testGuid);
    }

    @Override
    public void removeTest(RemoveTestCommand command) {
        this.testInterface.removeTest(command);
    }
}
