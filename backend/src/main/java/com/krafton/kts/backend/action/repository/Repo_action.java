package com.krafton.kts.backend.action.repository;

import com.krafton.kts.backend.action.domain.KTS_ACTION;
import com.krafton.kts.backend.action.domain.KTS_ACTION_TEMPLATE;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Repo_action {

    List<KTS_ACTION> findActionsByTESTCASE_GUID(String testcaseGuid);
    List<KTS_ACTION_TEMPLATE> findAllTemplate();
    void saveActionList(List<KTS_ACTION> list, List<String> removeList);
}
