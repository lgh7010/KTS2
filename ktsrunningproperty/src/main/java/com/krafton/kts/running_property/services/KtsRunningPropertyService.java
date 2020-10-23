package com.krafton.kts.running_property.services;

import com.krafton.kts.domain.running_property.command.AddRunningPropertyCommand;
import com.krafton.kts.domain.running_property.db.RUNNING_PROPERTY;
import com.krafton.kts.running_property.interfaces.KtsRunningPropertyInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KtsRunningPropertyService {

    private final KtsRunningPropertyInterface ktsRunningPropertyInterface;

    public void addRunningProperty(AddRunningPropertyCommand command){
        this.ktsRunningPropertyInterface.addRunningProperty(command);
    }
    public List<RUNNING_PROPERTY> findRunningProperty(String runningActionGuid){
        return this.ktsRunningPropertyInterface.findRunningProperty(runningActionGuid);
    }
}
