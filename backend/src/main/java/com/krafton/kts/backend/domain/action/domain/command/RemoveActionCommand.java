package com.krafton.kts.backend.domain.action.domain.command;

import lombok.Data;

@Data
public class RemoveActionCommand {
    private String testcaseGuid;

    public RemoveActionCommand(String testcaseGuid){
        this.testcaseGuid = testcaseGuid;
    }
}
