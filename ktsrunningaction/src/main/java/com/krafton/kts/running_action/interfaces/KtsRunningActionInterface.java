package com.krafton.kts.running_action.interfaces;

import com.krafton.kts.domain.running_action.command.AddRunningActionCommand;
import com.krafton.kts.domain.running_action.db.RUNNING_ACTION;
import com.krafton.kts.running_action.repository.KtsRunningActionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KtsRunningActionInterface {

    private final KtsRunningActionRepository ktsRunningActionRepository;

    public void addRunningAction(AddRunningActionCommand command){
        this.ktsRunningActionRepository.addRunningAction(command);
    }
    public List<RUNNING_ACTION> findRunningAction(String runningTestcaseGuid){
        return this.ktsRunningActionRepository.findRunningAction(runningTestcaseGuid);
    }
}
