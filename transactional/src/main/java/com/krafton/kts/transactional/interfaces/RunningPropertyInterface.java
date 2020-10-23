package com.krafton.kts.transactional.interfaces;

import com.krafton.kts.domain.running_property.command.AddRunningPropertyCommand;
import com.krafton.kts.domain.running_property.db.RUNNING_PROPERTY;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RunningPropertyInterface {
    public void addRunningProperty(AddRunningPropertyCommand command){

    }
    public List<RUNNING_PROPERTY> findRunningProperty(String runningActionGuid){
        return null;
    }
}
