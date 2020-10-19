package com.krafton.kts.backend.domain.test_rel_testcase.service;

import com.krafton.kts.backend.domain.test_rel_testcase.domain.db.TestRelTestcaseDerived;
import com.krafton.kts.backend.domain.test_rel_testcase.domain.command.SaveTestRelTestcaseCommand;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TestRelTestcaseService {

    List<TestRelTestcaseDerived> findTestRelTestcaseDerived(String testGuid);
}
