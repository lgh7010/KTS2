package com.krafton.kts.interfaces.repository.action;

import com.krafton.kts.domains.entity.KtsAction;
import com.krafton.kts.domains.entity.KtsActionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ActionInterface {
    List<KtsAction> findAction(String testcaseGuid);
    List<KtsActionTemplate> getActionTemplate();
    void saveAction(SaveActionCommand command);
    void updateActionId(UpdateActionIdCommand command);
    void removeAction(RemoveActionCommand command);
}
