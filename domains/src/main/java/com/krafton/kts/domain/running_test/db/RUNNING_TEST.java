package com.krafton.kts.domain.running_test.db;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class RUNNING_TEST {
    private String runningTestGuid;
    private String testGuid;
    private int currentRunningTestcaseOrder;
    private int currentRunningActionOrder;
    private String name;
    private String description;
    private Timestamp startAt;
    private Timestamp endAt;
    private String status;
}
