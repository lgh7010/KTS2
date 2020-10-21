package com.krafton.kts.backend.domain.running_property.interfaces;

import com.krafton.kts.backend.domain.running_property.domain.command.AddRunningPropertyCommand;
import com.krafton.kts.backend.domain.running_property.domain.db.RUNNING_PROPERTY;

import java.util.List;

public interface RunningPropertyInterface {
    void addRunningProperty(AddRunningPropertyCommand command);
    List<RUNNING_PROPERTY> findRunningProperty(String runningActionGuid);
}
