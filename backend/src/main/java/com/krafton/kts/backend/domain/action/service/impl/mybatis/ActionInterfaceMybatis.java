package com.krafton.kts.backend.domain.action.service.impl.mybatis;

import com.krafton.kts.backend.domain.action.domain.command.RemoveActionCommand;
import com.krafton.kts.backend.domain.action.domain.command.SaveActionCommand;
import com.krafton.kts.backend.domain.action.domain.command.UpdateActionIdCommand;
import com.krafton.kts.backend.domain.action.domain.db.KTS_ACTION;
import com.krafton.kts.backend.domain.action.domain.db.KTS_ACTION_TEMPLATE;
import com.krafton.kts.backend.domain.action.service.impl.ActionInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ActionInterfaceMybatis implements ActionInterface {

    @Autowired
    private ActionInterfaceMybatisMapper actionInterfaceMybatisMapper;

    @Override
    public List<KTS_ACTION> findAction(String testcaseGuid) {
        return this.actionInterfaceMybatisMapper.findAction(testcaseGuid);
    }

    @Override
    public List<KTS_ACTION_TEMPLATE> getActionTemplate() {
        return this.actionInterfaceMybatisMapper.getActionTemplate();
    }

    @Override
    public void saveAction(SaveActionCommand command) {
        this.actionInterfaceMybatisMapper.saveAction(command);
    }

    @Override
    public void updateActionId(UpdateActionIdCommand command) {
        this.actionInterfaceMybatisMapper.updateActionId(command);
    }

    @Override
    public void removeAction(RemoveActionCommand command) {
        this.actionInterfaceMybatisMapper.removeAction(command);
    }
}
