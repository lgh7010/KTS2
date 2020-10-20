package com.krafton.kts.backend.service.crossdomain.db;

import lombok.Data;

@Data
public class TEST_REL_TESTCASE_JOIN_TESTCASE {
    private String relationGuid;
    private String testGuid;
    private int listIndex;
    private String testcaseGuid;
    private String deleted;

    private String name;
    private String description;
}
