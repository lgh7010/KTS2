package com.krafton.kts.domains.entity;

import lombok.Data;

@Data
public class RunningTestcase {
    private String runningTestcaseGuid;
    private String testcaseGuid;
    private String runningTestGuid;
    private int runningTestcaseOrder;
}
