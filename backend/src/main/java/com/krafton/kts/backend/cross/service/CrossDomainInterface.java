package com.krafton.kts.backend.cross.service;

import com.krafton.kts.backend.domain.action.domain.command.SaveTestcaseCommand;

public interface CrossDomainInterface {
    void saveTestcase(SaveTestcaseCommand command);
}
