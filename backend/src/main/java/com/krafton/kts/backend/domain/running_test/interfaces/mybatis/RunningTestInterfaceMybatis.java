package com.krafton.kts.backend.domain.running_test.interfaces.mybatis;

import com.krafton.kts.backend.domain.running_test.domain.db.RUNNING_TEST;
import com.krafton.kts.backend.domain.running_test.interfaces.RunningTestInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RunningTestInterfaceMybatis implements RunningTestInterface {

    @Autowired
    private RunningTestInterfaceMybatisMapper runningTestInterfaceMybatisMapper;

    @Override
    public void addOrUpdateRunningTest(RUNNING_TEST runningTest) {
        this.runningTestInterfaceMybatisMapper.addOrUpdateRunningTest(runningTest);
    }

    @Override
    public RUNNING_TEST findRunningTest(String runningTestGuid) {
        return this.runningTestInterfaceMybatisMapper.findRunningTest(runningTestGuid);
    }

    @Override
    public List<RUNNING_TEST> findAllRunningTest() {
        return this.runningTestInterfaceMybatisMapper.findAllRunningTest();
    }
}
