package com.krafton.kts.commands.testreltestcase;

import lombok.Data;

@Data
public class RemoveTestRelTestcaseByTestGuidCommand {
    private String testGuid;

    public RemoveTestRelTestcaseByTestGuidCommand(String testGuid){
        this.testGuid = testGuid;
    }
}
