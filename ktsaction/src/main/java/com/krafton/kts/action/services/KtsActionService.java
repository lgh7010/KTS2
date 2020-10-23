package com.krafton.kts.action.services;

import com.krafton.kts.action.interfaces.KtsActionInterface;
import com.krafton.kts.domain.action.command.RemoveActionCommand;
import com.krafton.kts.domain.action.command.SaveActionCommand;
import com.krafton.kts.domain.action.command.UpdateActionIdCommand;
import com.krafton.kts.domain.action.db.KTS_ACTION;
import com.krafton.kts.domain.action.db.KTS_ACTION_TEMPLATE;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KtsActionService {

    private final KtsActionInterface ktsActionInterface;

    public List<KTS_ACTION> findAction(String testcaseGuid){
        return this.ktsActionInterface.findAction(testcaseGuid);
    }
    public List<KTS_ACTION_TEMPLATE> getActionTemplate(){
        return this.ktsActionInterface.getActionTemplate();
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
