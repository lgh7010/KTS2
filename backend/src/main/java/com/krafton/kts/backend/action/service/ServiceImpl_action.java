package com.krafton.kts.backend.action.service;

import com.krafton.kts.backend.action.domain.KTS_ACTION;
import com.krafton.kts.backend.action.repository.Repo_action;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ServiceImpl_action implements Service_action{

    private Repo_action repo_action;

    @Autowired
    public ServiceImpl_action(Repo_action repo_action){
        this.repo_action = repo_action;
    }

    @Override
    public List<KTS_ACTION> findActionsByTESTCASE_SEQ(int TESTCASE_SEQ) {
        return repo_action.findActionsByTESTCASE_SEQ(TESTCASE_SEQ);
    }
}
