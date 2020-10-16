package com.krafton.kts.backend.domain.test_rel_testcase.domain.db;

import lombok.Data;

@Data
public class TEST_REL_TESTCASE {
    private String relationGuid;
    private String testGuid;
    private int listIndex;
    private String testcaseGuid;
    private String deleted;
}
