package com.krafton.kts.interfaces.repository.action;

import com.krafton.kts.domains.entity.KtsAction;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SaveActionCommand {
    private Map<String, KtsAction> actionMap;
    private List<String> removeList;

    public SaveActionCommand(Map<String, KtsAction> currentTestcaseActions, List<String> removeActionGuidList) {
        this.actionMap = currentTestcaseActions;
        this.removeList = removeActionGuidList;
    }
}
