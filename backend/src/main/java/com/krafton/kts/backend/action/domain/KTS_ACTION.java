package com.krafton.kts.backend.action.domain;

import lombok.Data;

@Data
public class KTS_ACTION {
    private String ACTION_GUID;
    private String TESTCASE_GUID;
    private String IS_START;
    private String NEXT_ACTION_GUID;
    private String ACTION_ID;
    private float X_POS;
    private float Y_POS;
    private String DESCRIPTION;
    private String DELETED;
}
