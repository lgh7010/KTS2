package com.krafton.kts.domain.test_rel_testcase.command;

import lombok.Data;

@Data
public class RemoveTestRelTestcaseByTestGuidCommand {
    private String testGuid;

    public RemoveTestRelTestcaseByTestGuidCommand(String testGuid){
        this.testGuid = testGuid;
    }
}
