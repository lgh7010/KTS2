package com.krafton.kts.backend.test_rel_testcase.service;

import com.krafton.kts.backend.test_rel_testcase.domain.TEST_REL_TESTCASE;
import com.krafton.kts.backend.test_rel_testcase.domain.TestRelTestcaseDerived;
import com.krafton.kts.backend.test_rel_testcase.repository.Repo_test_rel_testcase;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ServiceImpl_test_rel_testcase implements Service_test_rel_testcase {

    private Repo_test_rel_testcase repo_test_rel_testcase;

    @Autowired
    public ServiceImpl_test_rel_testcase(Repo_test_rel_testcase repo_test_rel_testcase){
        this.repo_test_rel_testcase = repo_test_rel_testcase;
    }

    @Override
    public List<TEST_REL_TESTCASE> findTestRelTestcaseByTEST_SEQ(int testSeq) {
        if(testSeq < 1){
            List<TEST_REL_TESTCASE> list = new ArrayList<>();
            return list;
        }
        return this.repo_test_rel_testcase.findTestRelTestcaseByTEST_SEQ(testSeq);
    }

    @Override
    public void saveTestRelTestcase(List<TEST_REL_TESTCASE> rels, int testSeq, String testName, String testDescription, List<Integer> removeTestcaseGuidList) {
        this.repo_test_rel_testcase.saveTestRelTestcase(rels, testSeq, testSeq > 0 ? false : true, testName, testDescription, removeTestcaseGuidList);
    }

    @Override
    public List<TestRelTestcaseDerived> findTestRelTestcaseDerived(int testSeq) {
        return this.repo_test_rel_testcase.findTestRelTestcaseDerived(testSeq);
    }
}
