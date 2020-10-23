package com.krafton.kts.domain.action.command;

import lombok.Data;

@Data
public class RemoveActionCommand {
    private String testcaseGuid;

    public RemoveActionCommand(String testcaseGuid){
        this.testcaseGuid = testcaseGuid;
    }
}
