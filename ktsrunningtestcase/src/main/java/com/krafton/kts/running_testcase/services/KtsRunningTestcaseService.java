package com.krafton.kts.running_testcase.services;

import com.krafton.kts.domain.running_testcase.command.AddRunningTestcaseCommand;
import com.krafton.kts.domain.running_testcase.db.RUNNING_TESTCASE;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KtsRunningTestcaseService {
    public void addRunningTestcase(AddRunningTestcaseCommand command){

    }
    public List<RUNNING_TESTCASE> findRunningTestcase(String runningTestGuid){
        return null;
    }
}
