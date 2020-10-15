package com.krafton.kts.backend.test.service;

import com.krafton.kts.backend.test.domain.command.RemoveTestCommand;
import com.krafton.kts.backend.test.repository.Repo_test;
import com.krafton.kts.backend.test.domain.db.KTS_TEST;
import com.krafton.kts.backend.test.service.internal.RemoveTestService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ServiceImpl_test implements Service_test {

    private Repo_test testRepo;
    private RemoveTestService removeTestService;

    @Autowired
    public ServiceImpl_test(Repo_test testInfoRepo, RemoveTestService removeTestService){
        this.testRepo = testInfoRepo;
        this.removeTestService = removeTestService;
    }

    @Override
    public List<KTS_TEST> findAll() {
        return this.testRepo.findAllTest();
    }

    @Override
    public KTS_TEST find(int TEST_SEQ) {
        if(TEST_SEQ < 1){
            return null;
        }
        Optional<KTS_TEST> result = this.testRepo.findTestByTEST_SEQ(TEST_SEQ);
        if(result.isPresent()){
            return result.get();
        }
        return null;
    }

    @Override
    public void removeTest(RemoveTestCommand command) {
        if(command.getTestSeq() < 1){
            return;
        }
        this.removeTestService.RemoveTest(command);
    }
}
