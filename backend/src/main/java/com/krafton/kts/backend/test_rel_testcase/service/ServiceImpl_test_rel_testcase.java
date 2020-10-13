package com.krafton.kts.backend.test_rel_testcase.service;

import com.krafton.kts.backend.action.domain.KTS_ACTION;
import com.krafton.kts.backend.test_rel_testcase.domain.TEST_REL_TESTCASE;
import com.krafton.kts.backend.test_rel_testcase.repository.Repo_test_rel_testcase;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.List;

public class ServiceImpl_test_rel_testcase implements Service_test_rel_testcase {

    private Repo_test_rel_testcase repo_test_rel_testcase;

    @Autowired
    public ServiceImpl_test_rel_testcase(Repo_test_rel_testcase repo_test_rel_testcase){
        this.repo_test_rel_testcase = repo_test_rel_testcase;
    }

    @Override
    public List<TEST_REL_TESTCASE> findTestRelTestcaseByTEST_SEQ(int TEST_SEQ) {
        return this.repo_test_rel_testcase.findTestRelTestcaseByTEST_SEQ(TEST_SEQ);
    }

    @Override
    public void saveTestRelTestcase(List<TEST_REL_TESTCASE> rels, int TEST_SEQ, String testName, String testDescription) {
        this.repo_test_rel_testcase.saveTestRelTestcase(rels, TEST_SEQ, TEST_SEQ > 0 ? false : true, testName, testDescription);
    }
}
