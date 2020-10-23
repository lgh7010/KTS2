package com.krafton.kts.backend.interfaces;

import com.krafton.kts.domain.property.command.FindPropertiesCommand;
import com.krafton.kts.domain.property.command.RemovePropertiesCommand;
import com.krafton.kts.domain.property.command.SavePropertiesCommand;
import com.krafton.kts.domain.property.db.KTS_ACTION_PROPERTY;
import com.krafton.kts.domain.property.db.KTS_ACTION_PROPERTY_TEMPLATE;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyInterface {
    public List<KTS_ACTION_PROPERTY> findProperties(FindPropertiesCommand command){
        return null;
    }
    public List<KTS_ACTION_PROPERTY_TEMPLATE> getPropertyTemplate(String actionId){
        return null;
    }
    public void saveProperties(SavePropertiesCommand command){

    }
    public void removeProperties(RemovePropertiesCommand command){

    }
}
