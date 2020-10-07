package com.krafton.kts.backend.test.service;

import com.krafton.kts.backend.test.repository.Repo_test;
import com.krafton.kts.backend.test.domain.KTS_TEST;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
    public void removeTest(int TEST_SEQ) {
        this.testRepo.removeTest(TEST_SEQ);
    }
}
