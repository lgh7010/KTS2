package com.krafton.kts.domain.property.command;

import lombok.Data;

@Data
public class RemovePropertiesCommand {
    private String testcaseGuid;

    public RemovePropertiesCommand(String testcaseGuid){
        this.testcaseGuid = testcaseGuid;
    }
}
