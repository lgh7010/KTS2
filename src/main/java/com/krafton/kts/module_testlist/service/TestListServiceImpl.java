package com.krafton.kts.module_testlist.service;

import com.krafton.kts.module_repository.testinfo.TestInfoRepo;
import com.krafton.kts.module_testlist.domain.KTS_TEST;
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
}
