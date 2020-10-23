package com.krafton.kts.transactional.interfaces;

import com.krafton.kts.domain.running_test.db.RUNNING_TEST;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RunningTestInterface {
    public void addOrUpdateRunningTest(RUNNING_TEST runningTest){

    }
    public RUNNING_TEST findRunningTest(String runningTestGuid){
        return null;
    }
    public List<RUNNING_TEST> findAllRunningTest(){
        return null;
    }
}
