package com.krafton.kts.domain.running_property.db;

import lombok.Data;

@Data
public class RUNNING_PROPERTY {
    private String runningPropertyGuid;
    private String runningActionGuid;
    private String runningPropertyName;
    private String runningPropertyValue;
}
