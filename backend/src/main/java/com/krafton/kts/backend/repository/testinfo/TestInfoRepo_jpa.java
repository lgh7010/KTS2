package com.krafton.kts.backend.repository.testinfo;

import com.krafton.kts.backend.repository.testinfo.domain.TEST_REL_TESTCASE;
import com.krafton.kts.backend.testcaselist.domain.KTS_TESTCASE;
import com.krafton.kts.backend.testlist.domain.KTS_TEST;

import java.util.List;
import java.util.Optional;

public class TestInfoRepo_jpa implements TestInfoRepo {
    @Override
    public KTS_TEST saveTest(KTS_TEST test) {
        return null;
    }

    @Override
    public void removeTest(int TEST_SEQ) {

    }

    @Override
    public Optional<KTS_TEST> findTestByTEST_SEQ(int TEST_SEQ) {
        return Optional.empty();
    }

    @Override
    public Optional<KTS_TEST> findTestByNAME(String NAME) {
        return Optional.empty();
    }

    @Override
    public List<KTS_TEST> findAllTest() {
        return null;
    }

    @Override
    public KTS_TESTCASE saveTestcase(KTS_TESTCASE testcase) {
        return null;
    }

    @Override
    public Optional<KTS_TESTCASE> findTestcaseByTESTCASE_SEQ(int TESTCASE_SEQ) {
        return Optional.empty();
    }

    @Override
    public Optional<KTS_TESTCASE> findTestcaseByNAME(String NAME) {
        return Optional.empty();
    }

    @Override
    public List<KTS_TESTCASE> findAllTestcase() {
        return null;
    }

    @Override
    public List<TEST_REL_TESTCASE> findTestRelTestcaseByTEST_SEQ(int TEST_SEQ) {
        return null;
    }

    @Override
    public List<TEST_REL_TESTCASE> findTestRelTestcaseByTESTCASE_SEQ(int TESTCASE_SEQ) {
        return null;
    }

    @Override
    public List<KTS_TESTCASE> findTestcasesByTEST_SEQ(int TEST_SEQ) {
        return null;
    }
}
