package com.krafton.kts.transactional.interfaces;

import com.krafton.kts.domain.running_action.command.AddRunningActionCommand;
import com.krafton.kts.domain.running_action.db.RUNNING_ACTION;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RunningActionInterface {
    public void addRunningAction(AddRunningActionCommand command){

    }
    public List<RUNNING_ACTION> findRunningAction(String runningTestcaseGuid){
        return null;
    }
}
