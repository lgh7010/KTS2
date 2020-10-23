package com.krafton.kts.action.services;

import com.krafton.kts.action.interfaces.KtsActionInterface;
import com.krafton.kts.domain.action.command.RemoveActionCommand;
import com.krafton.kts.domain.action.command.SaveActionCommand;
import com.krafton.kts.domain.action.command.UpdateActionIdCommand;
import com.krafton.kts.domain.action.db.KTS_ACTION;
import com.krafton.kts.domain.action.db.KTS_ACTION_TEMPLATE;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class KtsActionService {

    private final KtsActionInterface ktsActionInterface;

    public List<KTS_ACTION> findAction(String testcaseGuid) {
        return this.ktsActionInterface.findAction(testcaseGuid);
    }
    public Map<String, KTS_ACTION_TEMPLATE> getActionTemplate() {
        List<KTS_ACTION_TEMPLATE> list = this.ktsActionInterface.getActionTemplate();
        Map<String, KTS_ACTION_TEMPLATE> ret = new HashMap<>();
        for (KTS_ACTION_TEMPLATE ktsActionTemplate : list) {
            ret.put(ktsActionTemplate.getActionId(), ktsActionTemplate);
        }
        return ret;
    }

    public void saveAction(SaveActionCommand command){
        this.ktsActionInterface.saveAction(command);
    }
    public void updateActionId(UpdateActionIdCommand command){
        this.ktsActionInterface.updateActionId(command);
    }
    public void removeAction(RemoveActionCommand command){
        this.ktsActionInterface.removeAction(command);
    }
}
