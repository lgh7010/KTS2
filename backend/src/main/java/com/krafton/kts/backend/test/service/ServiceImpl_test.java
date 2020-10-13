package com.krafton.kts.backend.test.service;

import com.krafton.kts.backend.test.repository.Repo_test;
import com.krafton.kts.backend.test.domain.KTS_TEST;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ServiceImpl_test implements Service_test {

    private Repo_test testRepo;

    @Autowired
    public ServiceImpl_test(Repo_test testInfoRepo){
        this.testRepo = testInfoRepo;
    }

    @Override
    public List<KTS_TEST> findAll() {
        return this.testRepo.findAllTest();
    }

    @Override
    public KTS_TEST find(int TEST_SEQ) {
        Optional<KTS_TEST> result = this.testRepo.findTestByTEST_SEQ(TEST_SEQ);
        if(result.isPresent()){
            return result.get();
        }
        return null;
    }

    @Override
    public void removeTest(int TEST_SEQ) {
        this.testRepo.removeTest(TEST_SEQ);
    }
}
