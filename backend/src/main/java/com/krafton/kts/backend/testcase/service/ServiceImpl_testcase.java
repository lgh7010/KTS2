package com.krafton.kts.backend.testcase.service;

import com.krafton.kts.backend.testcase.repository.Repo_testcase;
import com.krafton.kts.backend.testcase.domain.KTS_TESTCASE;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ServiceImpl_testcase implements Service_testcase {

    private Repo_testcase repo_testcase;

    @Autowired
    public ServiceImpl_testcase(Repo_testcase repo_testcase){
        this.repo_testcase = repo_testcase;
    }

    @Override
    public List<KTS_TESTCASE> findAll() {
        return this.repo_testcase.findAllTestcase();
    }

    @Override
    public List<KTS_TESTCASE> findTestcasesByTEST_SEQ(int TEST_SEQ) {
        return this.repo_testcase.findTestcasesByTEST_SEQ(TEST_SEQ);
    }

}
