package com.krafton.kts.backend.module_testlist.service;

import com.krafton.kts.backend.module_testlist.domain.KTS_TEST;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TestListService {

    List<KTS_TEST> findAll();
    void removeTest(int TEST_SEQ);

}
