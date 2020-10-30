package com.krafton.kts.service;

import com.krafton.kts.domains.entity.RunningTest;
import com.krafton.kts.interfaces.repository.runningtest.RunningTestInterface;
import com.krafton.kts.interfaces.service.FindRunningTestServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindRunningTestService implements FindRunningTestServiceInterface {

    private final RunningTestInterface runningTestInterface;

    @Override
    public List<RunningTest> findAllRunningTest() {
        return this.runningTestInterface.findAllRunningTest();
    }

}
