package com.krafton.kts.backend.domain.running_testcase.interfaces.mybatis;

import com.krafton.kts.commands.runningtestcase.AddRunningTestcaseCommand;
import com.krafton.kts.interfaces.repository.RunningTestcaseInterface;
import com.krafton.kts.domains.entity.RUNNING_TESTCASE;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RunningTestcaseInterfaceMybatis implements RunningTestcaseInterface {

    @Autowired
    private RunningTestcaseInterfaceMybatisMapper runningTestcaseInterfaceMybatisMapper;

    @Override
    public void addRunningTestcase(AddRunningTestcaseCommand command) {
        this.runningTestcaseInterfaceMybatisMapper.addRunningTestcase(command);
    }

    @Override
    public List<RUNNING_TESTCASE> findRunningTestcase(String runningTestGuid) {
        return this.runningTestcaseInterfaceMybatisMapper.findRunningTestcase(runningTestGuid);
    }
}
