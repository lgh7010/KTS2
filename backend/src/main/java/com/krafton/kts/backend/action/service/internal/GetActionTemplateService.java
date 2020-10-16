package com.krafton.kts.backend.action.service.internal;

import com.krafton.kts.backend.action.domain.db.KTS_ACTION_TEMPLATE;

import java.util.Map;

public interface GetActionTemplateService {
    Map<String, KTS_ACTION_TEMPLATE> getActionTemplate();
}
