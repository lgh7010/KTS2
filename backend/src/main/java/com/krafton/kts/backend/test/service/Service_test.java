package com.krafton.kts.backend.test.service;

import com.krafton.kts.backend.test.domain.KTS_TEST;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Service_test {

    List<KTS_TEST> findAll();
    KTS_TEST find(int TEST_SEQ);
    void removeTest(int TEST_SEQ);

}
