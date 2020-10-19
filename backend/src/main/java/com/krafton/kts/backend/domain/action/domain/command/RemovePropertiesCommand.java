package com.krafton.kts.backend.domain.action.domain.command;

import lombok.Data;

@Data
public class RemovePropertiesCommand {
    private String testcaseGuid;

    public RemovePropertiesCommand(String testcaseGuid){
        this.testcaseGuid = testcaseGuid;
    }
}
