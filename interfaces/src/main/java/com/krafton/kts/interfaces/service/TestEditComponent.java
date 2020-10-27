package com.krafton.kts.interfaces.service;

import com.krafton.kts.domains.derived.TEST_REL_TESTCASE_JOIN_TESTCASE;
import com.krafton.kts.domains.entity.KTS_TEST;
import com.krafton.kts.domains.entity.KTS_TESTCASE;
import com.krafton.kts.interfaces.repository.testreltestcase.SaveTestRelTestcaseCommand;

import java.util.List;

public interface TestEditComponent {
    KTS_TEST findTest(String testGuid);
    List<TEST_REL_TESTCASE_JOIN_TESTCASE> findTestRelTestcaseJoinTestcase(String testGuid);
    List<KTS_TESTCASE> findAllTestcase();
    void saveTestRelTestcase(SaveTestRelTestcaseCommand command);
}
