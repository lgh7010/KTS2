package com.krafton.kts.interfaces.repository.property;

import lombok.Data;

@Data
public class RemovePropertiesCommand {
    private String testcaseGuid;

    public RemovePropertiesCommand(String testcaseGuid){
        this.testcaseGuid = testcaseGuid;
    }
}
