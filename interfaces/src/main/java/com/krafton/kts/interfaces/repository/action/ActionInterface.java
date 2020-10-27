package com.krafton.kts.interfaces.repository.action;

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
