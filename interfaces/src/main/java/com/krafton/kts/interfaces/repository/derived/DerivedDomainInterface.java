package com.krafton.kts.interfaces.repository.derived;

import com.krafton.kts.domains.derived.TEST_REL_TESTCASE_JOIN_TESTCASE;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DerivedDomainInterface {
    List<TEST_REL_TESTCASE_JOIN_TESTCASE> findTestRelTestcaseJoinTestcase(String testGuid);
}
