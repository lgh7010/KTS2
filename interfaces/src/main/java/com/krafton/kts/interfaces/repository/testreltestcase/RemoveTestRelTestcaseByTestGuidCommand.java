package com.krafton.kts.interfaces.repository.testreltestcase;

import lombok.Data;

@Data
public class RemoveTestRelTestcaseByTestGuidCommand {
    private String testGuid;

    public RemoveTestRelTestcaseByTestGuidCommand(String testGuid){
        this.testGuid = testGuid;
    }
}
