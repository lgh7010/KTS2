package com.krafton.kts.domains.entity;

import lombok.Data;

@Data
public class RUNNING_TESTCASE {
    private String runningTestcaseGuid;
    private String testcaseGuid;
    private String runningTestGuid;
    private int runningTestcaseOrder;
}
