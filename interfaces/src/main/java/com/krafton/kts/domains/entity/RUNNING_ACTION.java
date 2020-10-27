package com.krafton.kts.domains.entity;

import lombok.Data;

@Data
public class RUNNING_ACTION {
    private String runningActionGuid;
    private String runningTestcaseGuid;
    private String actionId;
    private int actionOrder;
}
