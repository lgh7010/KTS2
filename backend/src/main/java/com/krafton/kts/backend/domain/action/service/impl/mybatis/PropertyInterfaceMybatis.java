package com.krafton.kts.backend.domain.action.service.impl.mybatis;

import com.krafton.kts.backend.domain.action.domain.command.SavePropertiesCommand;
import com.krafton.kts.backend.domain.action.domain.db.KTS_ACTION_PROPERTY;
import com.krafton.kts.backend.domain.action.domain.db.KTS_ACTION_PROPERTY_TEMPLATE;
import com.krafton.kts.backend.domain.action.service.impl.PropertyInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PropertyInterfaceMybatis implements PropertyInterface {

    @Autowired
    private PropertyInterfaceMybatisMapper propertyInterfaceMybatisMapper;

    @Override
    public List<KTS_ACTION_PROPERTY> findProperty(String actionGuid) {
        return this.propertyInterfaceMybatisMapper.findProperty(actionGuid);
    }

    @Override
    public List<KTS_ACTION_PROPERTY_TEMPLATE> getPropertyTemplate(String actionId) {
        return this.propertyInterfaceMybatisMapper.getPropertyTemplate(actionId);
    }

    @Override
    public void saveProperties(SavePropertiesCommand command) {
        this.propertyInterfaceMybatisMapper.saveProperties(command);
    }
}
