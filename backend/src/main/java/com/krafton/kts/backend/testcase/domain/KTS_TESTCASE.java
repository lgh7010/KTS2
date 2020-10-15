package com.krafton.kts.backend.testcase.domain;

import lombok.Data;

@Data
public class KTS_TESTCASE {
    private String testcaseGuid;
    private String name;
    private String description;
    private String deleted;
}
