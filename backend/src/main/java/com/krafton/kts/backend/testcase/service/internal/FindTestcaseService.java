package com.krafton.kts.backend.testcase.service.internal;

import com.krafton.kts.backend.testcase.domain.db.KTS_TESTCASE;

import java.util.List;

public interface FindTestcaseService {
    List<KTS_TESTCASE> findAll();
    KTS_TESTCASE findTestcase(String testcaseGuid);
}
