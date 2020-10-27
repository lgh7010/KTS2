package com.krafton.kts.interfaces.repository.derived;

import com.krafton.kts.domains.derived.TEST_REL_TESTCASE_JOIN_TESTCASE;

import java.util.List;

public interface DerivedDomainInterface {
    List<TEST_REL_TESTCASE_JOIN_TESTCASE> findTestRelTestcaseJoinTestcase(String testGuid);
}
