package com.krafton.kts.testcase.repository;

import com.krafton.kts.domain.testcase.command.RemoveTestcaseCommand;
import com.krafton.kts.domain.testcase.db.KTS_TESTCASE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KtsTestcaseRepository {
    void addTestcase(KTS_TESTCASE testcase);
    List<KTS_TESTCASE> findAll();
    KTS_TESTCASE findTestcase(String testcaseGuid);
    void removeTestcase(RemoveTestcaseCommand command);
}
