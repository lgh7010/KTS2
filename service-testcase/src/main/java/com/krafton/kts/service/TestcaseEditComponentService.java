package com.krafton.kts.service;

import com.krafton.kts.domains.entity.*;
import com.krafton.kts.interfaces.repository.action.ActionInterface;
import com.krafton.kts.interfaces.repository.action.SaveActionCommand;
import com.krafton.kts.interfaces.repository.derived.SaveTestcaseCommand;
import com.krafton.kts.interfaces.repository.property.FindPropertiesCommand;
import com.krafton.kts.interfaces.repository.property.PropertyInterface;
import com.krafton.kts.interfaces.repository.property.SavePropertiesCommand;
import com.krafton.kts.interfaces.repository.testcase.TestcaseInterface;
import com.krafton.kts.interfaces.service.TestcaseEditComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TestcaseEditComponentService implements TestcaseEditComponent {

    private final ActionInterface actionInterface;
    private final TestcaseInterface testcaseInterface;
    private final PropertyInterface propertyInterface;

    @Override
    public Map<String, KTS_ACTION_TEMPLATE> getActionTemplate() {
        List<KTS_ACTION_TEMPLATE> list = this.actionInterface.getActionTemplate();
        Map<String, KTS_ACTION_TEMPLATE> ret = new HashMap<>();
        for (KTS_ACTION_TEMPLATE ktsActionTemplate : list) {
            ret.put(ktsActionTemplate.getActionId(), ktsActionTemplate);
        }
        return ret;
    }

    @Override
    public List<KTS_ACTION> findAction(String testcaseGuid) {
        return this.actionInterface.findAction(testcaseGuid);
    }

    @Override
    public KTS_TESTCASE findTestcase(String testcaseGuid) {
        return this.testcaseInterface.findTestcase(testcaseGuid);
    }

    @Override
    @Transactional
    public Map<String, List<KTS_ACTION_PROPERTY>> findProperties(String testcaseGuid) {
        List<KTS_ACTION> actions = this.actionInterface.findAction(testcaseGuid);
        List<String> actionGuids = new ArrayList<>();
        for (KTS_ACTION action : actions) {
            actionGuids.add(action.getActionGuid());
        }

        Map<String, List<KTS_ACTION_PROPERTY>> ret = new HashMap<>();
        if(actions.stream().count() > 0){
            List<KTS_ACTION_PROPERTY> list = this.propertyInterface.findProperties(new FindPropertiesCommand(actionGuids));
            for (KTS_ACTION_PROPERTY property : list) {
                if(ret.containsKey(property.getActionGuid()) == false){
                    ret.put(property.getActionGuid(), new ArrayList<>());
                }
                ret.get(property.getActionGuid()).add(property);
            }
        }
        return ret;
    }

    @Override
    @Transactional
    public void saveTestcase(SaveTestcaseCommand command) {
        try {
            if(command.getCurrentTestcaseActions().size() > 0){
                this.actionInterface.saveAction(new SaveActionCommand(command.getCurrentTestcaseActions(), command.getRemoveActionGuidList()));

                Map<String, List<KTS_ACTION_PROPERTY>> currentTestcaseActionPropertyMap = command.getCurrentTestcaseActionPropertyMap();
                List<KTS_ACTION_PROPERTY> finalProperties = new ArrayList<>();
                List<String> actionGuids = new ArrayList<>();
                currentTestcaseActionPropertyMap.forEach((actionGuid, properties) -> {
                    actionGuids.add(actionGuid);
                    for (KTS_ACTION_PROPERTY property : properties) {
                        finalProperties.add(property);
                    }
                });
                if(actionGuids.stream().count() > 0){
                    this.propertyInterface.saveProperties(new SavePropertiesCommand(finalProperties, actionGuids));
                }
            }
            this.testcaseInterface.addTestcase(command.getTestcase());
        } catch(Exception e){
            throw e;
        }
    }

    @Override
    public List<KTS_ACTION_PROPERTY_TEMPLATE> getPropertyTemplate(String actionId) {
        return this.propertyInterface.getPropertyTemplate(actionId);
    }
}
