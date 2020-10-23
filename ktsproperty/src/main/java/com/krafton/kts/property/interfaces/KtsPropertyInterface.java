package com.krafton.kts.property.interfaces;

import com.krafton.kts.domain.property.command.FindPropertiesCommand;
import com.krafton.kts.domain.property.command.RemovePropertiesCommand;
import com.krafton.kts.domain.property.command.SavePropertiesCommand;
import com.krafton.kts.domain.property.db.KTS_ACTION_PROPERTY;
import com.krafton.kts.domain.property.db.KTS_ACTION_PROPERTY_TEMPLATE;
import com.krafton.kts.property.repository.KtsPropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KtsPropertyInterface {

    private final KtsPropertyRepository ktsPropertyRepository;

    public List<KTS_ACTION_PROPERTY> findProperties(FindPropertiesCommand command){
        return this.ktsPropertyRepository.findProperties(command);
    }
    public List<KTS_ACTION_PROPERTY_TEMPLATE> getPropertyTemplate(String actionId){
        return this.ktsPropertyRepository.getPropertyTemplate(actionId);
    }
    public void saveProperties(SavePropertiesCommand command){
        this.ktsPropertyRepository.saveProperties(command);
    }
    public void removeProperties(RemovePropertiesCommand command){
        this.ktsPropertyRepository.removeProperties(command);
    }
}
