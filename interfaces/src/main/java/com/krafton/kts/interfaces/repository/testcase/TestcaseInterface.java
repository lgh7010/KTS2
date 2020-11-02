package com.krafton.kts.interfaces.repository.testcase;

import com.krafton.kts.domains.entity.KtsTestcase;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TestcaseInterface {
    void addTestcase(KtsTestcase testcase);
    List<KtsTestcase> findAll();
    KtsTestcase findTestcase(String testcaseGuid);
    void removeTestcase(RemoveTestcaseCommand command);
}
