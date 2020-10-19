package com.krafton.kts.backend.domain.property.domain.command;

import lombok.Data;

@Data
public class RemovePropertiesCommand {
    private String testcaseGuid;

    public RemovePropertiesCommand(String testcaseGuid){
        this.testcaseGuid = testcaseGuid;
    }
}
