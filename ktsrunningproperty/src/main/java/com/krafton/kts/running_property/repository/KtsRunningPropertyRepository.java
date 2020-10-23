package com.krafton.kts.running_property.repository;

import com.krafton.kts.domain.running_property.command.AddRunningPropertyCommand;
import com.krafton.kts.domain.running_property.db.RUNNING_PROPERTY;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KtsRunningPropertyRepository {
    void addRunningProperty(AddRunningPropertyCommand command);
    List<RUNNING_PROPERTY> findRunningProperty(String runningActionGuid);
}
