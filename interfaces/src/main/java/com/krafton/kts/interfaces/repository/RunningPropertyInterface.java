package com.krafton.kts.interfaces.repository;

import com.krafton.kts.commands.runningproperty.AddRunningPropertyCommand;
import com.krafton.kts.domains.entity.RUNNING_PROPERTY;

import java.util.List;

public interface RunningPropertyInterface {
    void addRunningProperty(AddRunningPropertyCommand command);
    List<RUNNING_PROPERTY> findRunningProperty(String runningActionGuid);
}
