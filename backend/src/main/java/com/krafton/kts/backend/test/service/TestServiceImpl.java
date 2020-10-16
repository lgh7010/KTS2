package com.krafton.kts.backend.test.service;

import com.krafton.kts.backend.test.domain.command.RemoveTestCommand;
import com.krafton.kts.backend.test.domain.db.KTS_TEST;
import com.krafton.kts.backend.test.service.interfaces.TestInterface;
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
    public KTS_TEST findTest(int testSeq) {
        if(testSeq < 1){
            return null;
        }
        return this.testInterface.findTest(testSeq);
    }

    @Override
    public void removeTest(RemoveTestCommand command) {
        if(command.getTestSeq() < 1){
            return;
        }
        this.testInterface.RemoveTest(command);
    }
}
