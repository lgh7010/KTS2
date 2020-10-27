package com.krafton.kts.interfaces.repository.test;

import lombok.Data;

@Data
public class AddTestCommand {
    private String testGuid;
    private String testName;
    private String testDescription;

    public AddTestCommand(String testGuid, String testName, String testDescription){
        this.testGuid = testGuid;
        this.testName = testName;
        this.testDescription = testDescription;
    }
}
