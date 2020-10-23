package com.krafton.kts.action.repository;

import com.krafton.kts.domain.action.command.RemoveActionCommand;
import com.krafton.kts.domain.action.command.SaveActionCommand;
import com.krafton.kts.domain.action.command.UpdateActionIdCommand;
import com.krafton.kts.domain.action.db.KTS_ACTION;
import com.krafton.kts.domain.action.db.KTS_ACTION_TEMPLATE;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KtsActionRepository {
    List<KTS_ACTION> findAction(String testcaseGuid);
    List<KTS_ACTION_TEMPLATE> getActionTemplate();
    void saveAction(SaveActionCommand command);
    void updateActionId(UpdateActionIdCommand command);
    void removeAction(RemoveActionCommand command);
}
