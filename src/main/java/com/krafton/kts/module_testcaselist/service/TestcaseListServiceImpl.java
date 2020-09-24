package com.krafton.kts.module_testcaselist.service;

import com.krafton.kts.module_repository.testinfo.TestInfoRepo;
import com.krafton.kts.module_repository.testinfo.domain.TEST_REL_TESTCASE;
import com.krafton.kts.module_testcaselist.domain.KTS_TESTCASE;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestcaseListServiceImpl implements TestcaseListService{

    private TestInfoRepo testInfoRepo;

    public TestcaseListServiceImpl(TestInfoRepo testInfoRepo){
        this.testInfoRepo = testInfoRepo;
    }

    @Override
    public List<KTS_TESTCASE> findTestcaseByTEST_SEQ(int TEST_SEQ) {
        List<TEST_REL_TESTCASE> relList = this.testInfoRepo.findTestRelTestcaseByTEST_SEQ(TEST_SEQ);
        List<KTS_TESTCASE> ret = new ArrayList<>();
        for(TEST_REL_TESTCASE rel : relList){
            //이렇게 하면 TC 하나마다 쿼리날리므로 안됨. 근데 일단은 그냥 보류.
            Optional<KTS_TESTCASE> tc = this.testInfoRepo.findTestcaseByTESTCASE_SEQ(rel.getTESTCASE_SEQ());
            if(tc.isPresent()){
                ret.add(tc.get());
            }
        }
        return ret;
    }
}
