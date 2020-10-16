package com.krafton.kts.backend.cross.service;

import com.krafton.kts.backend.domain.action.domain.command.SaveCurrentTestcaseActionsCommand;

public interface SaveActionService {
    void saveAction(SaveCurrentTestcaseActionsCommand command);
}
