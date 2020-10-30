package com.krafton.kts.interfaces.repository.action;

import com.krafton.kts.domains.entity.KTS_ACTION;
import com.krafton.kts.domains.entity.KTS_ACTION_TEMPLATE;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActionInterface {
    List<KTS_ACTION> findAction(String testcaseGuid);
    List<KTS_ACTION_TEMPLATE> getActionTemplate();
    void saveAction(SaveActionCommand command);
    void updateActionId(UpdateActionIdCommand command);
    void removeAction(RemoveActionCommand command);
}
