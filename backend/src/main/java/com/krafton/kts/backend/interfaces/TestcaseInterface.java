package com.krafton.kts.backend.interfaces;

import com.krafton.kts.domain.testcase.command.RemoveTestcaseCommand;
import com.krafton.kts.domain.testcase.db.KTS_TESTCASE;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestcaseInterface {
    public void addTestcase(KTS_TESTCASE testcase){

    }
    public List<KTS_TESTCASE> findAll(){
        return null;
    }
    public KTS_TESTCASE findTestcase(String testcaseGuid){
        return null;
    }
    public void removeTestcase(RemoveTestcaseCommand command){

    }
}
