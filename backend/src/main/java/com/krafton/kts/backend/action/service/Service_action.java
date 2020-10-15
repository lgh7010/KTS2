package com.krafton.kts.backend.action.service;

import com.krafton.kts.backend.action.domain.KTS_ACTION;
import com.krafton.kts.backend.action.domain.KTS_ACTION_TEMPLATE;
import org.springframework.stereotype.Service;

import java.util.Dictionary;
import java.util.List;
import java.util.Map;

@Service
public interface Service_action {

    List<KTS_ACTION> findActionsByTESTCASE_GUID(String testcaseGuid);
    Map<String, KTS_ACTION_TEMPLATE> findAllTemplate();
    void saveActionList(Map<String, KTS_ACTION> map, List<String> removeList);
}
