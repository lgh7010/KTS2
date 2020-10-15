package com.krafton.kts.backend.action.repository;

import com.krafton.kts.backend.action.domain.KTS_ACTION;
import com.krafton.kts.backend.action.domain.KTS_ACTION_TEMPLETE;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Repo_action {

    List<KTS_ACTION> findActionsByTESTCASE_GUID(String TESTCASE_GUID);
    List<KTS_ACTION_TEMPLETE> findAllTemplete();
    void saveActionList(List<KTS_ACTION> list, List<String> removeList);
}
