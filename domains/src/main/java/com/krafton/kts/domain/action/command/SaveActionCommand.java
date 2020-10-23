package com.krafton.kts.domain.action.command;

import com.krafton.kts.domain.action.db.KTS_ACTION;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SaveActionCommand {
    private Map<String, KTS_ACTION> actionMap;
    private List<String> removeList;

    public SaveActionCommand(Map<String, KTS_ACTION> currentTestcaseActions, List<String> removeActionGuidList) {
        this.actionMap = currentTestcaseActions;
        this.removeList = removeActionGuidList;
    }
}
