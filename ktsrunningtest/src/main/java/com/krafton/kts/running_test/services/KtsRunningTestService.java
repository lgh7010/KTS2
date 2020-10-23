package com.krafton.kts.running_test.services;

import com.krafton.kts.domain.running_test.db.RUNNING_TEST;
import com.krafton.kts.running_test.interfaces.KtsRunningTestInterface;
import com.krafton.kts.running_test.repository.KtsRunningTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KtsRunningTestService {

    private final KtsRunningTestInterface ktsRunningTestInterface;

    public void addOrUpdateRunningTest(RUNNING_TEST runningTest){
        this.ktsRunningTestInterface.addOrUpdateRunningTest(runningTest);
    }
    public RUNNING_TEST findRunningTest(String runningTestGuid){
        return this.ktsRunningTestInterface.findRunningTest(runningTestGuid);
    }
    public List<RUNNING_TEST> findAllRunningTest(){
        return this.ktsRunningTestInterface.findAllRunningTest();
    }
}
