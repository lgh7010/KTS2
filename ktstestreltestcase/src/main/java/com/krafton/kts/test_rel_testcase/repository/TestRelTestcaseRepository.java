package com.krafton.kts.test_rel_testcase.repository;

import com.krafton.kts.domain.test_rel_testcase.command.RemoveTestRelTestcaseByTestGuidCommand;
import com.krafton.kts.domain.test_rel_testcase.command.RemoveTestRelTestcaseByTestcaseGuidCommand;
import com.krafton.kts.domain.test_rel_testcase.command.SaveTestRelTestcaseCommand;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRelTestcaseRepository {
    void saveTestRelTestcase(SaveTestRelTestcaseCommand command);
    void removeTestRelTestcaseByTestcaseGuid(RemoveTestRelTestcaseByTestcaseGuidCommand command);
    void removeTestRelTestcaseByTestGuid(RemoveTestRelTestcaseByTestGuidCommand command);
}
