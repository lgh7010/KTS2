package com.krafton.kts.domains.entity;

import lombok.Data;

@Data
public class TestRelTestcase {
    private String relationGuid;
    private String testGuid;
    private int listIndex;
    private String testcaseGuid;
    private String deleted;
}
