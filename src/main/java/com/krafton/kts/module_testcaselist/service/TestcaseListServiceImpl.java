package com.krafton.kts.module_testcaselist.service;

import com.krafton.kts.module_repository.testinfo.TestInfoRepo;
import com.krafton.kts.module_testcaselist.domain.KTS_TESTCASE;

import java.util.List;

public class TestcaseListServiceImpl implements TestcaseListService{

    private TestInfoRepo testInfoRepo;

    public TestcaseListServiceImpl(TestInfoRepo testInfoRepo){
        this.testInfoRepo = testInfoRepo;
    }

    @Override
    public List<KTS_TESTCASE> findTestcaseByTEST_SEQ(int TEST_SEQ) {
        return this.testInfoRepo.findTestcasesByTEST_SEQ(TEST_SEQ);
    }
}
