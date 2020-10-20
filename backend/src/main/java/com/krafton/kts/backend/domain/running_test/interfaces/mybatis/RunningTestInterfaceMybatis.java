package com.krafton.kts.backend.domain.running_test.interfaces.mybatis;

import com.krafton.kts.backend.domain.running_property.interfaces.mybatis.RunningPropertyInterfaceMybatisMapper;
import com.krafton.kts.backend.domain.running_test.domain.db.RUNNING_TEST;
import com.krafton.kts.backend.domain.running_test.interfaces.RunningTestInterface;
import org.springframework.beans.factory.annotation.Autowired;

public class RunningTestInterfaceMybatis implements RunningTestInterface {

    @Autowired
    private RunningTestInterfaceMybatisMapper runningTestInterfaceMybatisMapper;

    @Override
    public void addRunningTest(RUNNING_TEST runningTest) {
        this.runningTestInterfaceMybatisMapper.addRunningTest(runningTest);
    }

    @Override
    public RUNNING_TEST findRunningTest(String runningTestGuid) {
        return this.runningTestInterfaceMybatisMapper.findRunningTest(runningTestGuid);
    }
}
