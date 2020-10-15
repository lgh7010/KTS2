package com.krafton.kts.backend.testcase.service;

import com.krafton.kts.backend.testcase.domain.command.RemoveTestcaseCommand;
import com.krafton.kts.backend.testcase.repository.Repo_testcase;
import com.krafton.kts.backend.testcase.domain.db.KTS_TESTCASE;
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
        return repo_testcase.findAllTestcase();
    }

    @Override
    public void removeTestcase(RemoveTestcaseCommand command) {
        this.repo_testcase.removeTestcase(command);
    }

    @Override
    public KTS_TESTCASE findTestcase(String testcaseGuid) {
        return this.repo_testcase.findTestcase(testcaseGuid);
    }

    @Override
    public void addTestcase(KTS_TESTCASE testcase) {
        this.repo_testcase.addTestcase(testcase);
    }

}
