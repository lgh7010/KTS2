package com.krafton.kts.commands.crossdomain;

import lombok.Data;

@Data
public class OnFinishActionCommand {
    private String runningTestGuid;
    private String status;
}
