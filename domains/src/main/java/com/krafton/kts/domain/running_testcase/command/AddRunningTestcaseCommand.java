package com.krafton.kts.domain.running_testcase.command;

import com.krafton.kts.domain.running_testcase.db.RUNNING_TESTCASE;
import lombok.Data;

import java.util.List;

@Data
public class AddRunningTestcaseCommand {
    private List<RUNNING_TESTCASE> runningTestcases;

    public AddRunningTestcaseCommand(List<RUNNING_TESTCASE> runningTestcases){
        this.runningTestcases = runningTestcases;
    }
}
