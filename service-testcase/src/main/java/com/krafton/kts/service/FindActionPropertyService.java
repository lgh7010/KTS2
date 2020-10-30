package com.krafton.kts.service;

import com.krafton.kts.domains.entity.KtsAction;
import com.krafton.kts.domains.entity.KtsActionProperty;
import com.krafton.kts.interfaces.repository.action.ActionInterface;
import com.krafton.kts.interfaces.repository.property.FindPropertiesCommand;
import com.krafton.kts.interfaces.repository.property.PropertyInterface;
import com.krafton.kts.interfaces.service.FindActionPropertyServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FindActionPropertyService implements FindActionPropertyServiceInterface {

    private final ActionInterface actionInterface;
    private final PropertyInterface propertyInterface;

    @Transactional
    @Override
    public Map<String, List<KtsActionProperty>> findProperties(String testcaseGuid) {
        List<KtsAction> actions = this.actionInterface.findAction(testcaseGuid);
        List<String> actionGuids = new ArrayList<>();
        for (KtsAction action : actions) {
            actionGuids.add(action.getActionGuid());
        }

        Map<String, List<KtsActionProperty>> ret = new HashMap<>();
        if(actions.stream().count() > 0){
            List<KtsActionProperty> list = this.propertyInterface.findProperties(new FindPropertiesCommand(actionGuids));
            for (KtsActionProperty property : list) {
                if(ret.containsKey(property.getActionGuid()) == false){
                    ret.put(property.getActionGuid(), new ArrayList<>());
                }
                ret.get(property.getActionGuid()).add(property);
            }
        }
        return ret;
    }
}
