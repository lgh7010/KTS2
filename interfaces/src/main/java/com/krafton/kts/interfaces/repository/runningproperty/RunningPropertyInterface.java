package com.krafton.kts.interfaces.repository.runningproperty;

import com.krafton.kts.domains.entity.RunningProperty;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RunningPropertyInterface {
    void addRunningProperty(AddRunningPropertyCommand command);
    List<RunningProperty> findRunningProperty(String runningActionGuid);
}
