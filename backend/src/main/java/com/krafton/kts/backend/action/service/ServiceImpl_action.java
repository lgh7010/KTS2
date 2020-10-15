package com.krafton.kts.backend.action.service;

import com.krafton.kts.backend.action.domain.KTS_ACTION;
import com.krafton.kts.backend.action.domain.KTS_ACTION_TEMPLATE;
import com.krafton.kts.backend.action.repository.Repo_action;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ServiceImpl_action implements Service_action{

    private Repo_action repo_action;

    @Autowired
    public ServiceImpl_action(Repo_action repo_action){
        this.repo_action = repo_action;
    }

    @Override
    public List<KTS_ACTION> findActionsByTESTCASE_GUID(String testcaseGuid) {
        return repo_action.findActionsByTESTCASE_GUID(testcaseGuid);
    }

    @Override
    public Map<String, KTS_ACTION_TEMPLATE> findAllTemplate() {
        List<KTS_ACTION_TEMPLATE> list = this.repo_action.findAllTemplate();
        Map<String, KTS_ACTION_TEMPLATE> ret = new HashMap<>();
        for (Iterator<KTS_ACTION_TEMPLATE> iter = list.iterator(); iter.hasNext();){
            KTS_ACTION_TEMPLATE tp = iter.next();
            ret.put(tp.getActionId(), tp);
        }
        return ret;
    }

    @Override
    public void saveActionList(List<KTS_ACTION> list, List<String> removeList) {
        this.repo_action.saveActionList(list, removeList);
    }
}
