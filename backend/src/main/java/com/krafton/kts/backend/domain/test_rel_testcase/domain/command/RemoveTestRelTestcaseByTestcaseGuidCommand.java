package com.krafton.kts.backend.domain.test_rel_testcase.domain.command;

import lombok.Data;

@Data
public class RemoveTestRelTestcaseByTestcaseGuidCommand {
    private String testcaseGuid;

    public RemoveTestRelTestcaseByTestcaseGuidCommand(String testcaseGuid){
        this.testcaseGuid = testcaseGuid;
    }
}
