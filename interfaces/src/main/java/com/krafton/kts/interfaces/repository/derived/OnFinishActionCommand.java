package com.krafton.kts.interfaces.repository.derived;

import lombok.Data;

@Data
public class OnFinishActionCommand {
    private String runningTestGuid;
    private String status;
}
