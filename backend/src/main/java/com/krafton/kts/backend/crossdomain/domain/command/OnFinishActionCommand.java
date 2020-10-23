package com.krafton.kts.backend.crossdomain.domain.command;

import lombok.Data;

@Data
public class OnFinishActionCommand {
    private String runningTestGuid;
    private String status;
}
