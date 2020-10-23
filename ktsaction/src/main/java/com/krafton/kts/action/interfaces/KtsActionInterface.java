package com.krafton.kts.action.interfaces;

import com.krafton.kts.action.repository.KtsActionRepository;
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
public class KtsActionInterface {

    private final KtsActionRepository ktsActionRepository;

    public List<KTS_ACTION> findAction(String testcaseGuid){
        return this.ktsActionRepository.findAction(testcaseGuid);
    }
    public List<KTS_ACTION_TEMPLATE> getActionTemplate(){
        return this.ktsActionRepository.getActionTemplate();
    }
    public void saveAction(SaveActionCommand command){
        this.ktsActionRepository.saveAction(command);
    }
    public void updateActionId(UpdateActionIdCommand command){
        this.ktsActionRepository.updateActionId(command);
    }
    public void removeAction(RemoveActionCommand command){
        this.ktsActionRepository.removeAction(command);
    }
}
