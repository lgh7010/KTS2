package com.krafton.kts.interfaces.repository;


import com.krafton.kts.commands.action.RemoveActionCommand;
import com.krafton.kts.commands.action.SaveActionCommand;
import com.krafton.kts.commands.action.UpdateActionIdCommand;
import com.krafton.kts.domains.entity.KTS_ACTION;
import com.krafton.kts.domains.entity.KTS_ACTION_TEMPLATE;

import java.util.List;

public interface ActionInterface {
    List<KTS_ACTION> findAction(String testcaseGuid);
    List<KTS_ACTION_TEMPLATE> getActionTemplate();
    void saveAction(SaveActionCommand command);
    void updateActionId(UpdateActionIdCommand command);
    void removeAction(RemoveActionCommand command);
}
