package com.krafton.kts.running_action.services;

import com.krafton.kts.domain.running_action.command.AddRunningActionCommand;
import com.krafton.kts.domain.running_action.db.RUNNING_ACTION;
import com.krafton.kts.running_action.interfaces.KtsRunningActionInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KtsRunningActionService {

    private final KtsRunningActionInterface ktsRunningActionInterface;

    public void addRunningAction(AddRunningActionCommand command){
        this.ktsRunningActionInterface.addRunningAction(command);
    }
    public List<RUNNING_ACTION> findRunningAction(String runningTestcaseGuid){
        return this.ktsRunningActionInterface.findRunningAction(runningTestcaseGuid);
    }
}
