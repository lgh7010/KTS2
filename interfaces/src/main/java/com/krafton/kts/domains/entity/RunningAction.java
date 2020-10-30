package com.krafton.kts.domains.entity;

import lombok.Data;

@Data
public class RunningAction {
    private String runningActionGuid;
    private String runningTestcaseGuid;
    private String actionId;
    private int actionOrder;
}
