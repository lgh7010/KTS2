package com.krafton.kts.commands.property;

import lombok.Data;

import java.util.List;

@Data
public class FindPropertiesCommand {
    private List<String> actionGuids;

    public FindPropertiesCommand(List<String> list){
        this.actionGuids = list;
    }
}
