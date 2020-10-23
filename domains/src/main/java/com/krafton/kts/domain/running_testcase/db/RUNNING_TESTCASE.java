package com.krafton.kts.domain.running_testcase.db;

import lombok.Data;

@Data
public class RUNNING_TESTCASE {
    private String runningTestcaseGuid;
    private String testcaseGuid;
    private String runningTestGuid;
    private int runningTestcaseOrder;
}
