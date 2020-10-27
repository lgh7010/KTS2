package com.krafton.kts.commands.testreltestcase;

import lombok.Data;

@Data
public class RemoveTestRelTestcaseByTestcaseGuidCommand {
    private String testcaseGuid;

    public RemoveTestRelTestcaseByTestcaseGuidCommand(String testcaseGuid){
        this.testcaseGuid = testcaseGuid;
    }
}
