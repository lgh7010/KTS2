package com.krafton.kts.backend.action.domain;

import lombok.Data;

import java.util.List;

@Data
public class SavePropertiesCommand {
    private String actionGuid;
    private String actionId;
    private List<KTS_PROPERTY> properties;
}
