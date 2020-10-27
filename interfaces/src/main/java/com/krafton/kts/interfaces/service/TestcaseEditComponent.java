package com.krafton.kts.interfaces.service;

import com.krafton.kts.domains.entity.*;
import com.krafton.kts.interfaces.repository.derived.SaveTestcaseCommand;

import java.util.List;
import java.util.Map;

public interface TestcaseEditComponent {
    Map<String, KTS_ACTION_TEMPLATE> getActionTemplate();
    List<KTS_ACTION> findAction(String testcaseGuid);
    KTS_TESTCASE findTestcase(String testcaseGuid);
    Map<String, List<KTS_ACTION_PROPERTY>> findProperties(String testcaseGuid);
    void saveTestcase(SaveTestcaseCommand command);
    List<KTS_ACTION_PROPERTY_TEMPLATE> getPropertyTemplate(String actionId);
}
