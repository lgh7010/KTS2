package com.krafton.kts.backend.action.repository;

import com.krafton.kts.backend.action.domain.KTS_ACTION;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Repo_action {

    List<KTS_ACTION> findActionsByTESTCASE_SEQ(int TESTCASE_SEQ);
}
