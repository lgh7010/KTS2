package com.krafton.kts.backend.domain.action.domain.command;

import com.krafton.kts.backend.domain.action.domain.db.KTS_ACTION;
import com.krafton.kts.backend.domain.testcase.domain.db.KTS_TESTCASE;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SaveCurrentTestcaseActionsCommand {
    private Map<String, KTS_ACTION> currentTestcaseActions;
    private List<String> removeActionGuidList;
    private KTS_TESTCASE testcase;
}
