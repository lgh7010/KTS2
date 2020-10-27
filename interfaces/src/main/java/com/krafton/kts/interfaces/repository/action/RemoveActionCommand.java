package com.krafton.kts.interfaces.repository.action;

import lombok.Data;

@Data
public class RemoveActionCommand {
    private String testcaseGuid;

    public RemoveActionCommand(String testcaseGuid){
        this.testcaseGuid = testcaseGuid;
    }
}
