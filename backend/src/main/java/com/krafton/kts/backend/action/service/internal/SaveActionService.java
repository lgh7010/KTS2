package com.krafton.kts.backend.action.service.internal;

import com.krafton.kts.backend.action.domain.db.KTS_ACTION;

import java.util.List;
import java.util.Map;

public interface SaveActionService {
    void saveAction(Map<String, KTS_ACTION> map, List<String> removeList);
}
