package com.krafton.kts.interfaces.service;

import com.krafton.kts.domains.entity.KtsActionProperty;
import java.util.List;
import java.util.Map;

public interface FindActionPropertyServiceInterface {
    Map<String, List<KtsActionProperty>> findProperties(String testcaseGuid);
}
