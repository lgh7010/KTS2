package com.krafton.kts.running_property.interfaces;

import com.krafton.kts.domain.running_property.command.AddRunningPropertyCommand;
import com.krafton.kts.domain.running_property.db.RUNNING_PROPERTY;
import com.krafton.kts.running_property.repository.KtsRunningPropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KtsRunningPropertyInterface {

    private final KtsRunningPropertyRepository ktsRunningPropertyRepository;

    public void addRunningProperty(AddRunningPropertyCommand command){
        if(command.getRunningProperties().stream().count() > 0) {
            this.ktsRunningPropertyRepository.addRunningProperty(command);
        }
    }
    public List<RUNNING_PROPERTY> findRunningProperty(String runningActionGuid){
        return this.ktsRunningPropertyRepository.findRunningProperty(runningActionGuid);
    }
}
