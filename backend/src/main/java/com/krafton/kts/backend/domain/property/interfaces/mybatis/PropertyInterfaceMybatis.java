package com.krafton.kts.backend.domain.property.interfaces.mybatis;

import com.krafton.kts.commands.property.FindPropertiesCommand;
import com.krafton.kts.commands.property.RemovePropertiesCommand;
import com.krafton.kts.commands.property.SavePropertiesCommand;
import com.krafton.kts.domains.entity.KTS_ACTION_PROPERTY;
import com.krafton.kts.domains.entity.KTS_ACTION_PROPERTY_TEMPLATE;
import com.krafton.kts.interfaces.repository.PropertyInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PropertyInterfaceMybatis implements PropertyInterface {

    @Autowired
    private PropertyInterfaceMybatisMapper propertyInterfaceMybatisMapper;

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
