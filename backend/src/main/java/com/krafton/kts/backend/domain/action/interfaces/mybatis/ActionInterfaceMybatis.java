package com.krafton.kts.backend.domain.action.interfaces.mybatis;

import com.krafton.kts.commands.action.RemoveActionCommand;
import com.krafton.kts.commands.action.SaveActionCommand;
import com.krafton.kts.commands.action.UpdateActionIdCommand;
import com.krafton.kts.domains.entity.KTS_ACTION;
import com.krafton.kts.domains.entity.KTS_ACTION_TEMPLATE;
import com.krafton.kts.interfaces.repository.ActionInterface;
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
