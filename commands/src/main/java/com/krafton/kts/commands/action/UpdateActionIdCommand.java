package com.krafton.kts.commands.action;

import lombok.Data;

@Data
public class UpdateActionIdCommand {
    private String actionGuid;
    private String actionId;

    public UpdateActionIdCommand(String actionGuid, String actionId){
        this.actionGuid = actionGuid;
        this.actionId = actionId;
    }
}
