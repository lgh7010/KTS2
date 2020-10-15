package com.krafton.kts.backend.test.repository;

import com.krafton.kts.backend.test.domain.KTS_TEST;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Repo_test {

    KTS_TEST saveTest(KTS_TEST test);
    void removeTest(int testSeq);
    Optional<KTS_TEST> findTestByTEST_SEQ(int testSeq);
    Optional<KTS_TEST> findTestByNAME(String name);
    List<KTS_TEST> findAllTest();
}
