package com.krafton.kts.backend.action.domain.db;

import lombok.Data;

@Data
public class KTS_PROPERTY {

    private int propertySeq;
    private String propertyName;
    private String propertyValue;
    private String actionGuid;
    private String deleted;
}
