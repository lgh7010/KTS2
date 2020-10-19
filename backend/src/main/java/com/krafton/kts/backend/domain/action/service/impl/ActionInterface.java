package com.krafton.kts.backend.domain.action.service.impl;

import com.krafton.kts.backend.domain.action.domain.command.RemoveActionCommand;
import com.krafton.kts.backend.domain.action.domain.command.SaveActionCommand;
import com.krafton.kts.backend.domain.action.domain.command.UpdateActionIdCommand;
import com.krafton.kts.backend.domain.action.domain.db.KTS_ACTION;
import com.krafton.kts.backend.domain.action.domain.db.KTS_ACTION_TEMPLATE;

import java.util.List;
import java.util.Map;

public interface ActionInterface {
    List<KTS_ACTION> findAction(String testcaseGuid);
    List<KTS_ACTION_TEMPLATE> getActionTemplate();
    void saveAction(SaveActionCommand command);
    void updateActionId(UpdateActionIdCommand command);
    void removeAction(RemoveActionCommand command);
}
