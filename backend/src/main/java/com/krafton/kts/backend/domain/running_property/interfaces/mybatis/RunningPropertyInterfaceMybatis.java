package com.krafton.kts.backend.domain.running_property.interfaces.mybatis;

import com.krafton.kts.commands.runningproperty.AddRunningPropertyCommand;
import com.krafton.kts.domains.entity.RUNNING_PROPERTY;
import com.krafton.kts.interfaces.repository.RunningPropertyInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RunningPropertyInterfaceMybatis implements RunningPropertyInterface {

    @Autowired
    private RunningPropertyInterfaceMybatisMapper runningPropertyInterfaceMybatisMapper;

    @Override
    public void addRunningProperty(AddRunningPropertyCommand command) {
        if(command.getRunningProperties().stream().count() > 0){
            this.runningPropertyInterfaceMybatisMapper.addRunningProperty(command);
        }
    }

    @Override
    public List<RUNNING_PROPERTY> findRunningProperty(String runningActionGuid) {
        return this.runningPropertyInterfaceMybatisMapper.findRunningProperty(runningActionGuid);
    }
}
