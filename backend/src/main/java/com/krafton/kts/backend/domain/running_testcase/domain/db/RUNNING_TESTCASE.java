package com.krafton.kts.backend.domain.running_testcase.domain.db;

import lombok.Data;

@Data
public class RUNNING_TESTCASE {
    private String runningTestcaseGuid;
    private String testcaseGuid;
    private String runningTestGuid;
    private int runningTestcaseOrder;
}
