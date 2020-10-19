package com.krafton.kts.backend.domain.test_rel_testcase.service;

import com.krafton.kts.backend.domain.test_rel_testcase.domain.db.TestRelTestcaseDerived;
import com.krafton.kts.backend.domain.test_rel_testcase.domain.command.SaveTestRelTestcaseCommand;
import com.krafton.kts.backend.domain.test_rel_testcase.service.impl.TestRelTestcaseInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestRelTestcaseServiceImpl implements TestRelTestcaseService {

    private TestRelTestcaseInterface testRelTestcaseInterface;

    @Autowired
    public TestRelTestcaseServiceImpl(TestRelTestcaseInterface findTestRelTestcaseService){
        this.testRelTestcaseInterface = findTestRelTestcaseService;
    }

    @Override
    public List<TestRelTestcaseDerived> findTestRelTestcaseDerived(String testGuid) {
        return this.testRelTestcaseInterface.findTestRelTestcaseDerived(testGuid);
    }
}
