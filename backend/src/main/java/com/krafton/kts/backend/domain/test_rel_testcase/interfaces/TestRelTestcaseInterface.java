package com.krafton.kts.backend.domain.test_rel_testcase.interfaces;

import com.krafton.kts.backend.domain.test_rel_testcase.domain.command.RemoveTestRelTestcaseByTestGuidCommand;
import com.krafton.kts.backend.domain.test_rel_testcase.domain.command.RemoveTestRelTestcaseByTestcaseGuidCommand;
import com.krafton.kts.backend.domain.test_rel_testcase.domain.command.SaveTestRelTestcaseCommand;
import com.krafton.kts.backend.service.crossdomain.db.TEST_REL_TESTCASE_JOIN_TESTCASE;

import java.util.List;

public interface TestRelTestcaseInterface {
    List<TEST_REL_TESTCASE_JOIN_TESTCASE> findTestRelTestcaseJoinTestcase(String testGuid);
    void saveTestRelTestcase(SaveTestRelTestcaseCommand command);
    void removeTestRelTestcaseByTestcaseGuid(RemoveTestRelTestcaseByTestcaseGuidCommand command);
    void removeTestRelTestcaseByTestGuid(RemoveTestRelTestcaseByTestGuidCommand command);
}
