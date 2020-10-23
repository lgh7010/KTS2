package com.krafton.kts.crossdomain.repository;

import com.krafton.kts.crossdomain.db.TEST_REL_TESTCASE_JOIN_TESTCASE;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrossDomainRepository {
    List<TEST_REL_TESTCASE_JOIN_TESTCASE> findTestRelTestcaseJoinTestcase(String testGuid);
}
