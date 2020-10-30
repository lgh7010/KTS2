package com.krafton.kts.interfaces.repository.derived;

import com.krafton.kts.domains.entity.KtsAction;
import com.krafton.kts.domains.entity.KtsActionProperty;
import com.krafton.kts.domains.entity.KtsTestcase;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SaveTestcaseCommand {
    private Map<String, KtsAction> currentTestcaseActions;
    private Map<String, List<KtsActionProperty>> currentTestcaseActionPropertyMap;
    private List<String> removeActionGuidList;
    private KtsTestcase testcase;
}
