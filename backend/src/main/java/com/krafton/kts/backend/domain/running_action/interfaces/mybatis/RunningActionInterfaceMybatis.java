package com.krafton.kts.backend.domain.running_action.interfaces.mybatis;

import com.krafton.kts.backend.domain.running_action.domain.command.AddRunningActionCommand;
import com.krafton.kts.backend.domain.running_action.domain.db.RUNNING_ACTION;
import com.krafton.kts.backend.domain.running_action.interfaces.RunningActionInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RunningActionInterfaceMybatis implements RunningActionInterface {

    @Autowired
    private RunningActionInterfaceMybatisMapper runningActionInterfaceMybatisMapper;

    @Override
    public void addRunningAction(AddRunningActionCommand command) {
        this.runningActionInterfaceMybatisMapper.addRunningAction(command);
    }

    @Override
    public List<RUNNING_ACTION> findRunningAction(String runningTestcaseGuid) {
        return this.runningActionInterfaceMybatisMapper.findRunningAction(runningTestcaseGuid);
    }
}
