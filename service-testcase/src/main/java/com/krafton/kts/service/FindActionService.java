package com.krafton.kts.service;

import com.krafton.kts.domains.entity.KtsAction;
import com.krafton.kts.domains.entity.KtsActionTemplate;
import com.krafton.kts.interfaces.repository.action.ActionInterface;
import com.krafton.kts.interfaces.service.FindActionServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FindActionService implements FindActionServiceInterface {

    private final ActionInterface actionInterface;

    @Override
    public Map<String, KtsActionTemplate> getActionTemplate() {
        List<KtsActionTemplate> list = this.actionInterface.getActionTemplate();
        Map<String, KtsActionTemplate> ret = new HashMap<>();
        for (KtsActionTemplate ktsActionTemplate : list) {
            ret.put(ktsActionTemplate.getActionId(), ktsActionTemplate);
        }
        return ret;
    }

    @Override
    public List<KtsAction> findAction(String testcaseGuid) {
        return this.actionInterface.findAction(testcaseGuid);
    }
}
