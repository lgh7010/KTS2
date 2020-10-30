package com.krafton.kts.interfaces.repository.runningproperty;

import com.krafton.kts.domains.entity.RUNNING_PROPERTY;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RunningPropertyInterface {
    void addRunningProperty(AddRunningPropertyCommand command);
    List<RUNNING_PROPERTY> findRunningProperty(String runningActionGuid);
}
