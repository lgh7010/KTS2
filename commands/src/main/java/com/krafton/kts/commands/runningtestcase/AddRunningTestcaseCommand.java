package com.krafton.kts.commands.runningtestcase;

import com.krafton.kts.domains.entity.RUNNING_TESTCASE;
import lombok.Data;

import java.util.List;

@Data
public class AddRunningTestcaseCommand {
    private List<RUNNING_TESTCASE> runningTestcases;

    public AddRunningTestcaseCommand(List<RUNNING_TESTCASE> runningTestcases){
        this.runningTestcases = runningTestcases;
    }
}
