package com.krafton.kts.backend.service.command;

import com.krafton.kts.backend.domain.action.domain.db.KTS_ACTION;
import com.krafton.kts.backend.domain.property.domain.db.KTS_ACTION_PROPERTY;
import com.krafton.kts.backend.domain.testcase.domain.db.KTS_TESTCASE;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SaveTestcaseCommand {
    private Map<String, KTS_ACTION> currentTestcaseActions;
    private Map<String, List<KTS_ACTION_PROPERTY>> currentTestcaseActionPropertyMap;
    private List<String> removeActionGuidList;
    private KTS_TESTCASE testcase;
}
