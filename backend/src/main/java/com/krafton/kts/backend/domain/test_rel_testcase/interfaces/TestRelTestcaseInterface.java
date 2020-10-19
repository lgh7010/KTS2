package com.krafton.kts.backend.domain.test_rel_testcase.interfaces;

import com.krafton.kts.backend.domain.test_rel_testcase.domain.command.RemoveTestRelTestcaseByTestGuidCommand;
import com.krafton.kts.backend.domain.test_rel_testcase.domain.command.RemoveTestRelTestcaseByTestcaseGuidCommand;
import com.krafton.kts.backend.domain.test_rel_testcase.domain.command.SaveTestRelTestcaseCommand;
import com.krafton.kts.backend.domain.test_rel_testcase.domain.db.TestRelTestcaseDerived;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TestRelTestcaseInterface {
    List<TestRelTestcaseDerived> findTestRelTestcaseDerived(String testGuid);
    void saveTestRelTestcase(SaveTestRelTestcaseCommand command);
    void removeTestRelTestcaseByTestcaseGuid(RemoveTestRelTestcaseByTestcaseGuidCommand command);
    void removeTestRelTestcaseByTestGuid(RemoveTestRelTestcaseByTestGuidCommand command);
}
