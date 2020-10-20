package com.krafton.kts.backend.domain.property.interfaces.mybatis;

import com.krafton.kts.backend.domain.property.domain.command.FindPropertiesCommand;
import com.krafton.kts.backend.domain.property.domain.command.RemovePropertiesCommand;
import com.krafton.kts.backend.domain.property.domain.command.SavePropertiesCommand;
import com.krafton.kts.backend.domain.property.domain.db.KTS_ACTION_PROPERTY;
import com.krafton.kts.backend.domain.property.domain.db.KTS_ACTION_PROPERTY_TEMPLATE;
import com.krafton.kts.backend.domain.property.interfaces.PropertyInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class PropertyInterfaceMybatis implements PropertyInterface {

    @Autowired
    private PropertyInterfaceMybatisMapper propertyInterfaceMybatisMapper;

//    @Override
//    public List<KTS_ACTION_PROPERTY> findProperty(String actionGuid) {
//        return this.propertyInterfaceMybatisMapper.findProperty(actionGuid);
//    }

    @Override
    public List<KTS_ACTION_PROPERTY> findProperties(FindPropertiesCommand command) {
        return this.propertyInterfaceMybatisMapper.findProperties(command);
    }

    @Override
    public List<KTS_ACTION_PROPERTY_TEMPLATE> getPropertyTemplate(String actionId) {
        return this.propertyInterfaceMybatisMapper.getPropertyTemplate(actionId);
    }

    @Override
    public void saveProperties(SavePropertiesCommand command) {
        this.propertyInterfaceMybatisMapper.saveProperties(command);
    }

    @Override
    public void removeProperties(RemovePropertiesCommand command) {
        this.propertyInterfaceMybatisMapper.removeProperties(command);
    }
}
