package com.krafton.kts.running_test.interfaces;

import com.krafton.kts.domain.running_test.db.RUNNING_TEST;
import com.krafton.kts.running_test.repository.KtsRunningTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KtsRunningTestInterface {

    private final KtsRunningTestRepository ktsRunningTestRepository;

    public void addOrUpdateRunningTest(RUNNING_TEST runningTest){
        this.ktsRunningTestRepository.addOrUpdateRunningTest(runningTest);
    }
    public RUNNING_TEST findRunningTest(String runningTestGuid){
        return this.ktsRunningTestRepository.findRunningTest(runningTestGuid);
    }
    public List<RUNNING_TEST> findAllRunningTest(){
        return this.ktsRunningTestRepository.findAllRunningTest();
    }
}
