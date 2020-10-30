package com.krafton.kts.interfaces.service;

import com.krafton.kts.domains.entity.KtsAction;
import com.krafton.kts.domains.entity.KtsActionTemplate;
import java.util.List;
import java.util.Map;

public interface FindActionServiceInterface {
    Map<String, KtsActionTemplate> getActionTemplate();
    List<KtsAction> findAction(String testcaseGuid);
}
