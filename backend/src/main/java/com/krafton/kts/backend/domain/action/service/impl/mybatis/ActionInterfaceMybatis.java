package com.krafton.kts.backend.domain.action.service.impl.mybatis;

import com.krafton.kts.backend.domain.action.domain.command.SaveActionCommand;
import com.krafton.kts.backend.domain.action.domain.db.KTS_ACTION;
import com.krafton.kts.backend.domain.action.domain.db.KTS_ACTION_TEMPLATE;
import com.krafton.kts.backend.domain.action.service.impl.ActionInterface;
import org.apache.ibatis.annotations.MapKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public class ActionInterfaceMybatis implements ActionInterface {

    @Autowired
    private ActionInterfaceMybatisMapper actionInterfaceMybatisMapper;

    @Override
    public List<KTS_ACTION> findAction(String testcaseGuid) {
        return this.actionInterfaceMybatisMapper.findAction(testcaseGuid);
    }

    @MapKey("myKey")
    @Override
    public List<KTS_ACTION_TEMPLATE> getActionTemplate() {
        return this.actionInterfaceMybatisMapper.getActionTemplate();
    }

    @Override
    public void saveAction(SaveActionCommand command) {
        this.actionInterfaceMybatisMapper.saveAction(command);
    }
}
