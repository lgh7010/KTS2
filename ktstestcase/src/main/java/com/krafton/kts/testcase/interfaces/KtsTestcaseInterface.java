package com.krafton.kts.testcase.interfaces;

import com.krafton.kts.domain.testcase.command.RemoveTestcaseCommand;
import com.krafton.kts.domain.testcase.db.KTS_TESTCASE;
import com.krafton.kts.testcase.repository.KtsTestcaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KtsTestcaseInterface {

    private final KtsTestcaseRepository ktsTestcaseRepository;

    public void addTestcase(KTS_TESTCASE testcase) {
        this.ktsTestcaseRepository.addTestcase(testcase);
    }

    public List<KTS_TESTCASE> findAll() {
        return this.ktsTestcaseRepository.findAll();
    }

    public KTS_TESTCASE findTestcase(String testcaseGuid) {
        return this.ktsTestcaseRepository.findTestcase(testcaseGuid);
    }

    public void removeTestcase(RemoveTestcaseCommand command) {
        this.ktsTestcaseRepository.removeTestcase(command);
    }
}
