package com.krafton.kts.property.services;

import com.krafton.kts.domain.property.command.FindPropertiesCommand;
import com.krafton.kts.domain.property.command.RemovePropertiesCommand;
import com.krafton.kts.domain.property.command.SavePropertiesCommand;
import com.krafton.kts.domain.property.db.KTS_ACTION_PROPERTY;
import com.krafton.kts.domain.property.db.KTS_ACTION_PROPERTY_TEMPLATE;
import com.krafton.kts.property.interfaces.KtsPropertyInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KtsPropertyService {

    private final KtsPropertyInterface ktsPropertyInterface;

    public List<KTS_ACTION_PROPERTY> findProperties(FindPropertiesCommand command){
        return this.ktsPropertyInterface.findProperties(command);
    }
    public List<KTS_ACTION_PROPERTY_TEMPLATE> getPropertyTemplate(String actionId){
        return this.ktsPropertyInterface.getPropertyTemplate(actionId);
    }
    public void saveProperties(SavePropertiesCommand command){
        this.ktsPropertyInterface.saveProperties(command);
    }
    public void removeProperties(RemovePropertiesCommand command){
        this.ktsPropertyInterface.removeProperties(command);
    }
}
