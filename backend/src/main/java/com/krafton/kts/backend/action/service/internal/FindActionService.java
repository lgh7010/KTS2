package com.krafton.kts.backend.action.service.internal;

import com.krafton.kts.backend.action.domain.db.KTS_ACTION;

import java.util.List;

public interface FindActionService {
    List<KTS_ACTION> findAction(String testcaseGuid);
}
