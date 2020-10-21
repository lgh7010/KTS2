package com.krafton.kts.backend.domain.running_testcase.domain.command;

import com.krafton.kts.backend.domain.running_testcase.domain.db.RUNNING_TESTCASE;
import lombok.Data;

import java.util.List;

@Data
public class AddRunningTestcaseCommand {
    private List<RUNNING_TESTCASE> runningTestcases;

    public AddRunningTestcaseCommand(List<RUNNING_TESTCASE> runningTestcases){
        this.runningTestcases = runningTestcases;
    }
}
