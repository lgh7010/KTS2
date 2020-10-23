package com.krafton.kts.domain.test_rel_testcase.db;

import lombok.Data;

@Data
public class TEST_REL_TESTCASE {
    private String relationGuid;
    private String testGuid;
    private int listIndex;
    private String testcaseGuid;
    private String deleted;
}
