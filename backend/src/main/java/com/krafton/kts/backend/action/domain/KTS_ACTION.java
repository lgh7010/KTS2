package com.krafton.kts.backend.action.domain;

import lombok.Data;

@Data
public class KTS_ACTION {
    private int ACTION_SEQ;
    private int TESTCASE_SEQ;
    private String IS_START;
    private int NEXT_ACTION_SEQ;
    private String ACTION_ID;
    private String DESCRIPTION;
    private String PROPERTY_JSON;
    private String DELETED;
}
