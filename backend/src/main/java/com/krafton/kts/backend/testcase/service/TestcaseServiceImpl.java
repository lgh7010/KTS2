package com.krafton.kts.backend.testcase.service;

import com.krafton.kts.backend.testcase.domain.command.RemoveTestcaseCommand;
import com.krafton.kts.backend.testcase.domain.db.KTS_TESTCASE;
import com.krafton.kts.backend.testcase.service.internal.AddTestcaseService;
import com.krafton.kts.backend.testcase.service.internal.FindTestcaseService;
import com.krafton.kts.backend.testcase.service.internal.RemoveTestcaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestcaseServiceImpl implements TestcaseService {

    private AddTestcaseService addTestcaseService;
    private FindTestcaseService findTestcaseService;
    private RemoveTestcaseService removeTestcaseService;

    @Autowired
    public TestcaseServiceImpl(AddTestcaseService addTestcaseService, FindTestcaseService findTestcaseService, RemoveTestcaseService removeTestcaseService){
        this.addTestcaseService = addTestcaseService;
        this.findTestcaseService = findTestcaseService;
        this.removeTestcaseService = removeTestcaseService;
    }

    @Override
    public List<KTS_TESTCASE> findAll() {
        return this.findTestcaseService.findAll();
    }

    @Override
    public void removeTestcase(RemoveTestcaseCommand command) {
        this.removeTestcaseService.removeTestcase(command);
    }

    @Override
    public KTS_TESTCASE findTestcase(String testcaseGuid) {
        return this.findTestcaseService.findTestcase(testcaseGuid);
    }

    @Override
    public void addTestcase(KTS_TESTCASE testcase) {
        this.addTestcaseService.addTestcase(testcase);
    }

}
