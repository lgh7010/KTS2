package com.krafton.kts.backend.testcase.domain.db;

import lombok.Data;

@Data
public class KTS_TESTCASE {
    private String testcaseGuid;
    private String name;
    private String description;
    private String deleted;
}
