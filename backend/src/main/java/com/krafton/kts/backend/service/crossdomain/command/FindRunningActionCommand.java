package com.krafton.kts.backend.service.crossdomain.command;

import lombok.Data;

@Data
public class FindRunningActionCommand {
    private String runningTestGuid;
    private int currentRunningTestcaseOrder;
    private int currentRunningActionOrder;

    public FindRunningActionCommand(String runningTestGuid, int currentRunningTestcaseOrder, int currentRunningActionOrder){
        this.runningTestGuid = runningTestGuid;
        this.currentRunningTestcaseOrder = currentRunningTestcaseOrder;
        this.currentRunningActionOrder = currentRunningActionOrder;
    }
}
