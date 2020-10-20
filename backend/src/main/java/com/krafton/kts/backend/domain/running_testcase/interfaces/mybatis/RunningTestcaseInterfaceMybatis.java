package com.krafton.kts.backend.domain.running_testcase.interfaces.mybatis;

import com.krafton.kts.backend.domain.running_testcase.domain.db.RUNNING_TESTCASE;
import com.krafton.kts.backend.domain.running_testcase.interfaces.RunningTestcaseInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RunningTestcaseInterfaceMybatis implements RunningTestcaseInterface {

    @Autowired
    private RunningTestcaseInterfaceMybatisMapper runningTestcaseInterfaceMybatisMapper;

    @Override
    public void addRunningTestcase(List<RUNNING_TESTCASE> runningTestcase) {
        this.runningTestcaseInterfaceMybatisMapper.addRunningTestcase(runningTestcase);
    }

    @Override
    public List<RUNNING_TESTCASE> findRunningTestcase(String runningTestGuid) {
        return this.runningTestcaseInterfaceMybatisMapper.findRunningTestcase(runningTestGuid);
    }
}
