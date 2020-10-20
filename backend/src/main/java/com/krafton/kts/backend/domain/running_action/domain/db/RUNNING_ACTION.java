package com.krafton.kts.backend.domain.running_action.domain.db;

import lombok.Data;

@Data
public class RUNNING_ACTION {
    private String runningActionGuid;
    private String runningTestcaseGuid;
    private String actionId;
    private int actionOrder;
}
