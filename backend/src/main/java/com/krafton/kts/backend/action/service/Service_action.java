package com.krafton.kts.backend.action.service;

import com.krafton.kts.backend.action.domain.KTS_ACTION;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Service_action {

    List<KTS_ACTION> findActionsByTESTCASE_SEQ(int TESTCASE_SEQ);
}
