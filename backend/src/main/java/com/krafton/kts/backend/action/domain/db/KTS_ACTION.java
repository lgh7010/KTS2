package com.krafton.kts.backend.action.domain.db;

import lombok.Data;

@Data
public class KTS_ACTION {
    private String actionGuid;
    private String testcaseGuid;
    private String isStart;
    private String nextActionGuid;
    private String actionId;
    private float x;
    private float y;
    private String description;
    private String deleted;
}
