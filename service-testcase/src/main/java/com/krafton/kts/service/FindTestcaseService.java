package com.krafton.kts.service;

import com.krafton.kts.domains.entity.KtsTestcase;
import com.krafton.kts.interfaces.repository.testcase.TestcaseInterface;
import com.krafton.kts.interfaces.service.FindTestcaseServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindTestcaseService implements FindTestcaseServiceInterface {

    private final TestcaseInterface testcaseInterface;

    @Override
    public KtsTestcase findTestcase(String testcaseGuid) {
        return this.testcaseInterface.findTestcase(testcaseGuid);
    }

    @Override
    public List<KtsTestcase> findAllTestcase() {
        return this.testcaseInterface.findAll();
    }
}
