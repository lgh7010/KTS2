package com.krafton.kts.backend.domain.action.service.interfaces;

import com.krafton.kts.backend.domain.action.domain.db.KTS_ACTION;
import com.krafton.kts.backend.domain.action.domain.db.KTS_ACTION_TEMPLATE;

import java.util.List;
import java.util.Map;

public interface ActionInterface {
    List<KTS_ACTION> findAction(String testcaseGuid);
    Map<String, KTS_ACTION_TEMPLATE> getActionTemplate();
    void saveAction(Map<String, KTS_ACTION> map, List<String> removeList);
}
