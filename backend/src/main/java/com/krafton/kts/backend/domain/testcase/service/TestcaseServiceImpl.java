package com.krafton.kts.backend.domain.testcase.service;

import com.krafton.kts.backend.domain.testcase.domain.command.RemoveTestcaseCommand;
import com.krafton.kts.backend.domain.testcase.domain.db.KTS_TESTCASE;
import com.krafton.kts.backend.domain.testcase.service.impl.TestcaseInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class TestcaseServiceImpl implements TestcaseService {

    private TestcaseInterface testcaseInterface;

    @Autowired
    public TestcaseServiceImpl(TestcaseInterface addTestcaseService){
        this.testcaseInterface = addTestcaseService;
    }

    @Override
    public List<KTS_TESTCASE> findAll() {
        return this.testcaseInterface.findAll();
    }

    @Override
    @Transactional
    public void removeTestcase(RemoveTestcaseCommand command) {
        this.testcaseInterface.removeTestcase(command);
    }

    @Override
    public KTS_TESTCASE findTestcase(String testcaseGuid) {
        return this.testcaseInterface.findTestcase(testcaseGuid);
    }

    @Override
    public void addTestcase(KTS_TESTCASE testcase) {
        this.testcaseInterface.addTestcase(testcase);
    }

}
