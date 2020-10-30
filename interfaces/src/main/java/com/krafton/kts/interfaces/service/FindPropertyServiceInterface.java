package com.krafton.kts.interfaces.service;

import com.krafton.kts.domains.entity.KtsActionPropertyTemplate;
import java.util.List;

public interface FindPropertyServiceInterface {
    List<KtsActionPropertyTemplate> getPropertyTemplate(String actionId);
}
