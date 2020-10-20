package com.krafton.kts.backend.domain.running_property.interfaces.mybatis;

import com.krafton.kts.backend.domain.running_property.domain.db.RUNNING_PROPERTY;
import com.krafton.kts.backend.domain.running_property.interfaces.RunningPropertyInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RunningPropertyInterfaceMybatis implements RunningPropertyInterface {

    @Autowired
    private RunningPropertyInterfaceMybatisMapper runningPropertyInterfaceMybatisMapper;

    @Override
    public void addRunningProperty(List<RUNNING_PROPERTY> properties) {
        this.runningPropertyInterfaceMybatisMapper.addRunningProperty(properties);
    }

    @Override
    public List<RUNNING_PROPERTY> findRunningProperty(String runningActionGuid) {
        return this.runningPropertyInterfaceMybatisMapper.findRunningProperty(runningActionGuid);
    }
}
