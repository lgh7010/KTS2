package com.krafton.kts.backend.domain.property.domain.command;

import lombok.Data;

import java.util.List;

@Data
public class FindPropertiesCommand {
    private List<String> actionGuids;

    public FindPropertiesCommand(List<String> list){
        this.actionGuids = list;
    }
}
