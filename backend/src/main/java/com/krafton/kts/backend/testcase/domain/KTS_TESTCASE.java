package com.krafton.kts.backend.testcase.domain;

import lombok.Data;

@Data
public class KTS_TESTCASE {
    private int TESTCASE_SEQ;
    private String NAME;
    private String DESCRIPTION;
    private String DELETED;
}