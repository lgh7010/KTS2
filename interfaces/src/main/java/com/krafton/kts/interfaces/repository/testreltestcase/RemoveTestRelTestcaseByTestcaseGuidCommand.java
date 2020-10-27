package com.krafton.kts.interfaces.repository.testreltestcase;

import lombok.Data;

@Data
public class RemoveTestRelTestcaseByTestcaseGuidCommand {
    private String testcaseGuid;

    public RemoveTestRelTestcaseByTestcaseGuidCommand(String testcaseGuid){
        this.testcaseGuid = testcaseGuid;
    }
}
