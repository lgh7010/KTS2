package com.krafton.kts.domains.entity;

import lombok.Data;

@Data
public class KtsTestcase {
    private String testcaseGuid;
    private String name;
    private String description;
    private String deleted;
}
