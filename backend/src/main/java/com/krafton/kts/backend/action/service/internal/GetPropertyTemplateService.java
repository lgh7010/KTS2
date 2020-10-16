package com.krafton.kts.backend.action.service.internal;

import com.krafton.kts.backend.action.domain.db.KTS_PROPERTY_TEMPLATE;

import java.util.List;

public interface GetPropertyTemplateService {
    List<KTS_PROPERTY_TEMPLATE> getPropertyTemplate(String actionId);
}
