package com.krafton.kts.backend.action.domain;

import com.krafton.kts.backend.testcase.domain.KTS_TESTCASE;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SaveCurrentTestcaseActionsCommand {
    private Map<String, KTS_ACTION> currentTestcaseActions;
    private List<String> removeActionGuidList;
    private KTS_TESTCASE testcase;
}
