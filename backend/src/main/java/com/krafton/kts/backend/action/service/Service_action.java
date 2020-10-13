package com.krafton.kts.backend.action.service;

import com.krafton.kts.backend.action.domain.KTS_ACTION;
import com.krafton.kts.backend.action.domain.KTS_ACTION_TEMPLETE;
import org.springframework.stereotype.Service;

import java.util.Dictionary;
import java.util.List;
import java.util.Map;

@Service
public interface Service_action {

    List<KTS_ACTION> findActionsByTESTCASE_SEQ(int TESTCASE_SEQ);
    Map<String, KTS_ACTION_TEMPLETE> findAllTemplete();
}
