package com.krafton.kts.crossdomain.response;

import com.krafton.kts.domain.running_action.db.RUNNING_ACTION;
import com.krafton.kts.domain.running_property.db.RUNNING_PROPERTY;
import com.krafton.kts.domain.running_test.db.RUNNING_TEST;
import lombok.Data;

import java.util.List;

@Data
public class NextTestInstructionResponse {
    private RUNNING_TEST runningTest;
    private RUNNING_ACTION runningAction;
    private List<RUNNING_PROPERTY> runningProperties;
    private String isFinished = "N";
}
