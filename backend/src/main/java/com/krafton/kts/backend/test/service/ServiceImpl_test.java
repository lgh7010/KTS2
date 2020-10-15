package com.krafton.kts.backend.test.service;

import com.krafton.kts.backend.test.domain.command.RemoveTestCommand;
import com.krafton.kts.backend.test.domain.db.KTS_TEST;
import com.krafton.kts.backend.test.service.internal.FindTestService;
import com.krafton.kts.backend.test.service.internal.RemoveTestService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ServiceImpl_test implements Service_test {

    private RemoveTestService removeTestService;
    private FindTestService findTestService;

    @Autowired
    public ServiceImpl_test(RemoveTestService removeTestService, FindTestService findTestService){
        this.removeTestService = removeTestService;
        this.findTestService = findTestService;
    }

    @Override
    public List<KTS_TEST> findAllTest() {
        return this.findTestService.findAllTest();
    }

    @Override
    public KTS_TEST findTest(int testSeq) {
        if(testSeq < 1){
            return null;
        }
        return this.findTestService.findTest(testSeq);
    }

    @Override
    public void removeTest(RemoveTestCommand command) {
        if(command.getTestSeq() < 1){
            return;
        }
        this.removeTestService.RemoveTest(command);
    }
}
