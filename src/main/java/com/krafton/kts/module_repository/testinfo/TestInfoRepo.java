package com.krafton.kts.module_repository.testinfo;

import com.krafton.kts.module_testcaselist.domain.KTS_TESTCASE;
import com.krafton.kts.module_testlist.domain.KTS_TEST;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestInfoRepo {

    //TEST
    KTS_TEST saveTest(KTS_TEST test);
    Optional<KTS_TEST> findTestByTEST_SEQ(int TEST_SEQ);
    Optional<KTS_TEST> findTestByNAME(String NAME);
    List<KTS_TEST> findAllTest();

    //TESTCASE
    KTS_TESTCASE saveTestcase(KTS_TESTCASE testcase);
    Optional<KTS_TESTCASE> findTestcaseByTESTCASE_SEQ(int TESTCASE_SEQ);
    Optional<KTS_TESTCASE> findTestcaseByNAME(String NAME);
    List<KTS_TESTCASE> findTestcasesByTEST_SEQ(int TEST_SEQ);
    List<KTS_TESTCASE> findAllTestcase();
}
