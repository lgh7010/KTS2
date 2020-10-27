package com.krafton.kts.interfaces.repository;

import com.krafton.kts.domains.derived.TEST_REL_TESTCASE_JOIN_TESTCASE;

import java.util.List;

public interface CrossDomainInterface {
    List<TEST_REL_TESTCASE_JOIN_TESTCASE> findTestRelTestcaseJoinTestcase(String testGuid);
}
