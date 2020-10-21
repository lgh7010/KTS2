package com.krafton.kts.backend.service.crossdomain.command;

import lombok.Data;

@Data
public class OnFinishActionCommand {
    private String runningTestGuid;
    private int currentRunningTestcaseOrder;
    private int currentRunningActionOrder;
    private String status;
}
