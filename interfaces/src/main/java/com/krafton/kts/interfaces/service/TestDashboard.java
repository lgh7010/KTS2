package com.krafton.kts.interfaces.service;

import com.krafton.kts.domains.entity.RUNNING_TEST;
import com.krafton.kts.interfaces.repository.derived.NextTestInstructionResponse;
import com.krafton.kts.interfaces.repository.derived.OnFinishActionCommand;
import com.krafton.kts.interfaces.repository.derived.RunTestCommand;

import java.util.List;

public interface TestDashboard {
    List<RUNNING_TEST> findAllRunningTest();
    NextTestInstructionResponse runTest(RunTestCommand command);
    NextTestInstructionResponse onFinishAction(OnFinishActionCommand command);
}
