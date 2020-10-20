package com.krafton.kts.backend.service.crossdomain.command;

import lombok.Data;

@Data
public class FindRunningActionCommand {
    private String runningTestGuid;
    private int currentTestcaseIndex;
    private int currentActionIndex;

    public FindRunningActionCommand(String runningTestGuid, int currentTestcaseIndex, int currentActionIndex){
        this.runningTestGuid = runningTestGuid;
        this.currentTestcaseIndex = currentTestcaseIndex;
        this.currentActionIndex = currentActionIndex;
    }
}
