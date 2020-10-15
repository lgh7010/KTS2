package com.krafton.kts.backend.test_rel_testcase.domain;

import lombok.Data;

@Data
public class TestRelTestcaseDerived {
    private int relationSeq;
    private int testSeq;
    private int listIndex;
    private String testcaseGuid;
    private String deleted;

    private String name;
    private String description;
}
