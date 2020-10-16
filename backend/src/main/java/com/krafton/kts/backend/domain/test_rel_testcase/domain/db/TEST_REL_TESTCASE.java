package com.krafton.kts.backend.domain.test_rel_testcase.domain.db;

import lombok.Data;

@Data
public class TEST_REL_TESTCASE {
    private int relationSeq;
    private int testSeq;
    private int listIndex;
    private String testcaseGuid;
    private String deleted;
}
