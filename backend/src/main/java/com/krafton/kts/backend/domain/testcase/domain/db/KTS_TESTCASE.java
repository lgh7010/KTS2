package com.krafton.kts.backend.domain.testcase.domain.db;

import lombok.Data;

@Data
public class KTS_TESTCASE {
    private String testcaseGuid;
    private String name;
    private String description;
    private String deleted;
}
