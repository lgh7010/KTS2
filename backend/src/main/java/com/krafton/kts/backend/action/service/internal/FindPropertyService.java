package com.krafton.kts.backend.action.service.internal;

import com.krafton.kts.backend.action.domain.db.KTS_PROPERTY;

import java.util.List;

public interface FindPropertyService {
    List<KTS_PROPERTY> findProperty(String actionGuid);
}
