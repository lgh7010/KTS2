package com.krafton.kts.backend.test.repository;

import com.krafton.kts.backend.test.domain.db.KTS_TEST;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Repo_test {

    void removeTest(int testSeq);
    Optional<KTS_TEST> findTestByTEST_SEQ(int testSeq);
    List<KTS_TEST> findAllTest();
}
