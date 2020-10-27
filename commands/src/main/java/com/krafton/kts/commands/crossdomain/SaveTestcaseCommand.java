package com.krafton.kts.commands.crossdomain;

import com.krafton.kts.domains.entity.KTS_ACTION;
import com.krafton.kts.domains.entity.KTS_ACTION_PROPERTY;
import com.krafton.kts.domains.entity.KTS_TESTCASE;
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
