package com.krafton.kts.domains.entity;

import lombok.Data;

@Data
public class KtsAction {
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
