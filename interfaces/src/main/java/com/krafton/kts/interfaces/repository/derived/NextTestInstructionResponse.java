package com.krafton.kts.interfaces.repository.derived;

import com.krafton.kts.domains.entity.RunningAction;
import com.krafton.kts.domains.entity.RunningProperty;
import com.krafton.kts.domains.entity.RunningTest;
import lombok.Data;

import java.util.List;

@Data
public class NextTestInstructionResponse {
    private RunningTest runningTest;
    private RunningAction runningAction;
    private List<RunningProperty> runningProperties;
    private String isFinished = "N";
}
