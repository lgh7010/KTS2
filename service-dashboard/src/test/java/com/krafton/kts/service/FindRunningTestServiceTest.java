package com.krafton.kts.service;

import com.krafton.kts.domains.entity.RunningTest;
import com.krafton.kts.interfaces.repository.runningtest.RunningTestInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class FindRunningTestServiceTest {

    private FindRunningTestService findRunningTestService;

    @Mock
    private RunningTestInterface runningTestInterface;

    @BeforeEach
    public void beforeEach(){
        this.runningTestInterface = mock(RunningTestInterface.class);
        this.findRunningTestService = new FindRunningTestService(this.runningTestInterface);
    }

    @Test
    public void findAllRunningTest(){
        List<RunningTest> runningTestList = new ArrayList<>();
        RunningTest runningTest = new RunningTest();
        runningTest.setRunningTestGuid("guid1");
        runningTestList.add(runningTest);
        given(this.runningTestInterface.findAllRunningTest()).willReturn(runningTestList);

        List<RunningTest> res = this.findRunningTestService.findAllRunningTest();

        assertEquals(runningTestList.stream().count(), res.stream().count());
        for(int i = 0; i < runningTestList.stream().count(); i++){
            assertEquals(runningTestList.get(i).getRunningTestGuid(), res.get(i).getRunningTestGuid());
        }
    }
}
