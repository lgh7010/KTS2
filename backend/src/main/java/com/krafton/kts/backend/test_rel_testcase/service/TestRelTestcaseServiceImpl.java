package com.krafton.kts.backend.test_rel_testcase.service;

import com.krafton.kts.backend.test_rel_testcase.domain.db.TestRelTestcaseDerived;
import com.krafton.kts.backend.test_rel_testcase.domain.command.TestRelTestcaseSaveCommand;
import com.krafton.kts.backend.test_rel_testcase.service.interfaces.TestRelTestcaseInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestRelTestcaseServiceImpl implements TestRelTestcaseService {

    private TestRelTestcaseInterface testRelTestcaseInterface;

    @Autowired
    public TestRelTestcaseServiceImpl(TestRelTestcaseInterface findTestRelTestcaseService){
        this.testRelTestcaseInterface = findTestRelTestcaseService;
    }

    @Override
    public void saveTestRelTestcase(TestRelTestcaseSaveCommand command) {
        this.testRelTestcaseInterface.saveTestRelTestcase(command);
    }

    @Override
    public List<TestRelTestcaseDerived> findTestRelTestcaseDerived(int testSeq) {
        return this.testRelTestcaseInterface.findTestRelTestcaseDerived(testSeq);
    }
}
