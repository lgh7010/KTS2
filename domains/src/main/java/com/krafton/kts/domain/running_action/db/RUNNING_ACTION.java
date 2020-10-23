package com.krafton.kts.domain.running_action.db;

import lombok.Data;

@Data
public class RUNNING_ACTION {
    private String runningActionGuid;
    private String runningTestcaseGuid;
    private String actionId;
    private int actionOrder;
}
