package com.krafton.kts.backend.test.service.internal;

import com.krafton.kts.backend.test.domain.db.KTS_TEST;

import java.util.List;

public interface FindTestService {
    //find와 findAll은 너무 유사해서 굳이 서비스 분리 안하고 그냥 통합.
    KTS_TEST findTest(int testSeq);
    List<KTS_TEST> findAllTest();
}
