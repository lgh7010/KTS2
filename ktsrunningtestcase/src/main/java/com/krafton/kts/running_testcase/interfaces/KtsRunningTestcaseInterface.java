package com.krafton.kts.running_testcase.interfaces;

import com.krafton.kts.domain.running_testcase.command.AddRunningTestcaseCommand;
import com.krafton.kts.domain.running_testcase.db.RUNNING_TESTCASE;
import com.krafton.kts.running_testcase.repository.KtsRunningTestcaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KtsRunningTestcaseInterface {

    private final KtsRunningTestcaseRepository ktsRunningTestcaseRepository;

    public void addRunningTestcase(AddRunningTestcaseCommand command){
        this.ktsRunningTestcaseRepository.addRunningTestcase(command);
    }
    public List<RUNNING_TESTCASE> findRunningTestcase(String runningTestGuid){
        return this.ktsRunningTestcaseRepository.findRunningTestcase(runningTestGuid);
    }
}
