package com.krafton.kts.backend.action.service.internal;

import com.krafton.kts.backend.action.domain.command.SavePropertiesCommand;

public interface SavePropertyService {
    void saveProperties(SavePropertiesCommand command);
}
