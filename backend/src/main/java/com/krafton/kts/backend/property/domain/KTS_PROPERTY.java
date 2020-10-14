package com.krafton.kts.backend.property.domain;

import lombok.Data;

@Data
public class KTS_PROPERTY {

    private int PROPERTY_SEQ;
    private String PROPERTY_NAME;
    private String PROPERTY_VALUE;
    private String ACTION_GUID;
    private String DELETED;
}
