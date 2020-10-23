package com.krafton.kts.testcase.services;

import com.krafton.kts.domain.testcase.command.RemoveTestcaseCommand;
import com.krafton.kts.domain.testcase.db.KTS_TESTCASE;
import com.krafton.kts.testcase.interfaces.KtsTestcaseInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KtsTestcaseService {

    private final KtsTestcaseInterface testcaseInterface;

    public void addTestcase(KTS_TESTCASE testcase){
        this.testcaseInterface.addTestcase(testcase);
    }
    public List<KTS_TESTCASE> findAll(){
        return this.testcaseInterface.findAll();
    }
    public KTS_TESTCASE findTestcase(String testcaseGuid){
        return this.testcaseInterface.findTestcase(testcaseGuid);
    }
    public void removeTestcase(RemoveTestcaseCommand command){
        this.testcaseInterface.removeTestcase(command);
    }
}
