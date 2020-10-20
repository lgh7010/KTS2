package com.krafton.kts.backend.service.crossdomain.response;

import com.krafton.kts.backend.domain.property.domain.db.KTS_ACTION_PROPERTY;
import com.krafton.kts.backend.domain.running_action.domain.db.RUNNING_ACTION;
import com.krafton.kts.backend.domain.running_property.domain.db.RUNNING_PROPERTY;
import com.krafton.kts.backend.domain.running_test.domain.db.RUNNING_TEST;
import lombok.Data;

import java.util.List;

@Data
public class NextTestInstructionResponse {
    private RUNNING_TEST runningTest;
    private RUNNING_ACTION runningAction;
    private List<RUNNING_PROPERTY> runningProperties;
}
