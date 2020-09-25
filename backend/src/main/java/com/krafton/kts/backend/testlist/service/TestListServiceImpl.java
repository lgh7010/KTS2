package com.krafton.kts.backend.testlist.service;

import com.krafton.kts.backend.repository.testinfo.TestInfoRepo;
import com.krafton.kts.backend.testlist.domain.KTS_TEST;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestListServiceImpl implements TestListService {

    private TestInfoRepo testInfoRepo;

    @Autowired
    public TestListServiceImpl(TestInfoRepo testInfoRepo){
        this.testInfoRepo = testInfoRepo;
    }

    @Override
    public List<KTS_TEST> findAll() {
        return this.testInfoRepo.findAllTest();
    }

    @Override
    public void removeTest(int TEST_SEQ) {
        this.testInfoRepo.removeTest(TEST_SEQ);
    }
}
