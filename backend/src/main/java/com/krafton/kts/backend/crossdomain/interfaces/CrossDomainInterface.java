package com.krafton.kts.backend.crossdomain.interfaces;

import com.krafton.kts.backend.crossdomain.domain.db.TEST_REL_TESTCASE_JOIN_TESTCASE;

import java.util.List;

public interface CrossDomainInterface {
    List<TEST_REL_TESTCASE_JOIN_TESTCASE> findTestRelTestcaseJoinTestcase(String testGuid);
}
