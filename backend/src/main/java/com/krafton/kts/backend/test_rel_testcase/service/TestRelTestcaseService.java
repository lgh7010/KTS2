package com.krafton.kts.backend.test_rel_testcase.service;

import com.krafton.kts.backend.test_rel_testcase.domain.db.TestRelTestcaseDerived;
import com.krafton.kts.backend.test_rel_testcase.domain.command.TestRelTestcaseSaveCommand;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TestRelTestcaseService {

    void saveTestRelTestcase(TestRelTestcaseSaveCommand command);
    List<TestRelTestcaseDerived> findTestRelTestcaseDerived(int testSeq);
}
