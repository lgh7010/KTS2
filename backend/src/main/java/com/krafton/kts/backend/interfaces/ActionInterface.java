package com.krafton.kts.backend.interfaces;

import com.krafton.kts.domain.action.command.RemoveActionCommand;
import com.krafton.kts.domain.action.command.SaveActionCommand;
import com.krafton.kts.domain.action.command.UpdateActionIdCommand;
import com.krafton.kts.domain.action.db.KTS_ACTION;
import com.krafton.kts.domain.action.db.KTS_ACTION_TEMPLATE;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActionInterface {

    public List<KTS_ACTION> findAction(String testcaseGuid){
        return null;
    }
    public List<KTS_ACTION_TEMPLATE> getActionTemplate(){
        return null;
    }
    public void saveAction(SaveActionCommand command){

    }
    public void updateActionId(UpdateActionIdCommand command){

    }
    public void removeAction(RemoveActionCommand command){

    }
}
