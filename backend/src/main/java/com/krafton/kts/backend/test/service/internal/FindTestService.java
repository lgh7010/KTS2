package com.krafton.kts.backend.test.service.internal;

import com.krafton.kts.backend.test.domain.db.KTS_TEST;

import java.util.List;

public interface FindTestService {
    KTS_TEST findTest(int testSeq);
    List<KTS_TEST> findAllTest();
}
