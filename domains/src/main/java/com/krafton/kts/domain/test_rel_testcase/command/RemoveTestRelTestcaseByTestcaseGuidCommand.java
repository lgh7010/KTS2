package com.krafton.kts.domain.test_rel_testcase.command;

import lombok.Data;

@Data
public class RemoveTestRelTestcaseByTestcaseGuidCommand {
    private String testcaseGuid;

    public RemoveTestRelTestcaseByTestcaseGuidCommand(String testcaseGuid){
        this.testcaseGuid = testcaseGuid;
    }
}
