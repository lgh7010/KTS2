package com.krafton.kts.backend.domain.running_property.domain.db;

import lombok.Data;

@Data
public class RUNNING_PROPERTY {
    private String runningPropertyGuid;
    private String runningActionGuid;
    private String runningPropertyName;
    private String runningPropertyValue;
}
