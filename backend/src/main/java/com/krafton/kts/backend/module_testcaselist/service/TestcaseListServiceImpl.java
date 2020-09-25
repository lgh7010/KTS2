package com.krafton.kts.backend.module_testcaselist.service;

import com.krafton.kts.backend.module_repository.testinfo.TestInfoRepo;
import com.krafton.kts.backend.module_repository.testinfo.domain.TEST_REL_TESTCASE;
import com.krafton.kts.backend.module_testcaselist.domain.KTS_TESTCASE;

import java.util.List;

public class TestcaseListServiceImpl implements TestcaseListService{

    private TestInfoRepo testInfoRepo;

    public TestcaseListServiceImpl(TestInfoRepo testInfoRepo){
        this.testInfoRepo = testInfoRepo;
    }

    @Override
    public List<KTS_TESTCASE> findTestcasesByTEST_SEQ(int TEST_SEQ) {
        return this.testInfoRepo.findTestcasesByTEST_SEQ(TEST_SEQ);
    }

    @Override
    public List<TEST_REL_TESTCASE> findTestRelTestcaseByTEST_SEQ(int TEST_SEQ) {
        return this.testInfoRepo.findTestRelTestcaseByTEST_SEQ(TEST_SEQ);
    }
}
