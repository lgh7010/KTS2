package com.krafton.kts.interfaces.repository.runningtestcase;

import com.krafton.kts.domains.entity.RunningTestcase;
import lombok.Data;

import java.util.List;

@Data
public class AddRunningTestcaseCommand {
    private List<RunningTestcase> runningTestcases;

    public AddRunningTestcaseCommand(List<RunningTestcase> runningTestcases){
        this.runningTestcases = runningTestcases;
    }
}
