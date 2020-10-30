package com.krafton.kts.interfaces.service;

import com.krafton.kts.interfaces.repository.derived.NextTestInstructionResponse;
import com.krafton.kts.interfaces.repository.derived.OnFinishActionCommand;
import com.krafton.kts.interfaces.repository.derived.RunTestCommand;

public interface TestManagingServiceInterface {
    NextTestInstructionResponse runTest(RunTestCommand command);
    NextTestInstructionResponse onFinishAction(OnFinishActionCommand command);
}
