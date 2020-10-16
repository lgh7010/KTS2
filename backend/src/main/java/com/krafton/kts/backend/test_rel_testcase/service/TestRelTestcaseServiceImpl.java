package com.krafton.kts.backend.test_rel_testcase.service;

import com.krafton.kts.backend.test_rel_testcase.domain.db.TestRelTestcaseDerived;
import com.krafton.kts.backend.test_rel_testcase.domain.command.TestRelTestcaseSaveCommand;
import com.krafton.kts.backend.test_rel_testcase.service.internal.FindTestRelTestcaseService;
import com.krafton.kts.backend.test_rel_testcase.service.internal.SaveTestRelTestcaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestRelTestcaseServiceImpl implements TestRelTestcaseService {

    private SaveTestRelTestcaseService saveTestRelTestcaseService;
    private FindTestRelTestcaseService findTestRelTestcaseService;

    @Autowired
    public TestRelTestcaseServiceImpl(SaveTestRelTestcaseService saveTestRelTestcaseService, FindTestRelTestcaseService findTestRelTestcaseService){
        this.saveTestRelTestcaseService = saveTestRelTestcaseService;
        this.findTestRelTestcaseService = findTestRelTestcaseService;
    }

    @Override
    public void saveTestRelTestcase(TestRelTestcaseSaveCommand command) {
        this.saveTestRelTestcaseService.saveTestRelTestcase(command);
    }

    @Override
    public List<TestRelTestcaseDerived> findTestRelTestcaseDerived(int testSeq) {
        return this.findTestRelTestcaseService.findTestRelTestcaseDerived(testSeq);
    }
}
