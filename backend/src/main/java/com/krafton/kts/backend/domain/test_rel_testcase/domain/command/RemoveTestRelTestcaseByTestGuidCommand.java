package com.krafton.kts.backend.domain.test_rel_testcase.domain.command;

import lombok.Data;

@Data
public class RemoveTestRelTestcaseByTestGuidCommand {
    private String testGuid;

    public RemoveTestRelTestcaseByTestGuidCommand(String testGuid){
        this.testGuid = testGuid;
    }
}
