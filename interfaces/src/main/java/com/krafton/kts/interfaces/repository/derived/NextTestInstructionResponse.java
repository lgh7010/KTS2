package com.krafton.kts.interfaces.repository.derived;

import com.krafton.kts.domains.entity.RUNNING_ACTION;
import com.krafton.kts.domains.entity.RUNNING_PROPERTY;
import com.krafton.kts.domains.entity.RUNNING_TEST;
import lombok.Data;

import java.util.List;

@Data
public class NextTestInstructionResponse {
    private RUNNING_TEST runningTest;
    private RUNNING_ACTION runningAction;
    private List<RUNNING_PROPERTY> runningProperties;
    private String isFinished = "N";
}
