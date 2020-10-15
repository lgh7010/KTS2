package com.krafton.kts.backend.test_rel_testcase.service;

import com.krafton.kts.backend.test_rel_testcase.domain.db.TestRelTestcaseDerived;
import com.krafton.kts.backend.test_rel_testcase.domain.command.TestRelTestcaseSaveCommand;
import com.krafton.kts.backend.test_rel_testcase.repository.Repo_test_rel_testcase;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ServiceImpl_test_rel_testcase implements Service_test_rel_testcase {

    private Repo_test_rel_testcase repo_test_rel_testcase;

    @Autowired
    public ServiceImpl_test_rel_testcase(Repo_test_rel_testcase repo_test_rel_testcase){
        this.repo_test_rel_testcase = repo_test_rel_testcase;
    }

    @Override
    public void saveTestRelTestcase(TestRelTestcaseSaveCommand command) {
        this.repo_test_rel_testcase.saveTestRelTestcase(command);
    }

    @Override
    public List<TestRelTestcaseDerived> findTestRelTestcaseDerived(int testSeq) {
        return this.repo_test_rel_testcase.findTestRelTestcaseDerived(testSeq);
    }
}
