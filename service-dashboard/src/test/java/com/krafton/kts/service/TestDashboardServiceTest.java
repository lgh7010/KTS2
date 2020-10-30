package com.krafton.kts.service;

import com.krafton.kts.interfaces.repository.action.ActionInterface;
import com.krafton.kts.interfaces.repository.derived.DerivedDomainInterface;
import com.krafton.kts.interfaces.repository.property.PropertyInterface;
import com.krafton.kts.interfaces.repository.runningaction.RunningActionInterface;
import com.krafton.kts.interfaces.repository.runningproperty.RunningPropertyInterface;
import com.krafton.kts.interfaces.repository.runningtest.RunningTestInterface;
import com.krafton.kts.interfaces.repository.runningtestcase.RunningTestcaseInterface;
import com.krafton.kts.interfaces.repository.test.TestInterface;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = TestManagingService.class)
public class TestDashboardServiceTest {

    @MockBean
    TestManagingService testDashboardService;

    @MockBean
    RunningTestInterface runningTestInterface;
    @MockBean
    TestInterface testInterface;
    @MockBean
    ActionInterface actionInterface;
    @MockBean
    DerivedDomainInterface derivedDomainInterface;
    @MockBean
    RunningActionInterface runningActionInterface;
    @MockBean
    PropertyInterface propertyInterface;
    @MockBean
    RunningPropertyInterface runningPropertyInterface;
    @MockBean
    RunningTestcaseInterface runningTestcaseInterface;

    @Test
    public void findAllRunningTest(){

    }
    @Test
    public void RunTestCommand(){

    }
    @Test
    public void OnFinishActionCommand(){

    }
}
