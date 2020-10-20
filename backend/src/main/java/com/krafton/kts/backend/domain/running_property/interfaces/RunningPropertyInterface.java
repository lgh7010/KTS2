package com.krafton.kts.backend.domain.running_property.interfaces;

import com.krafton.kts.backend.domain.running_property.domain.db.RUNNING_PROPERTY;

import java.util.List;

public interface RunningPropertyInterface {
    void addRunningProperty(List<RUNNING_PROPERTY> properties);
    List<RUNNING_PROPERTY> findRunningProperty(String runningActionGuid);
}
