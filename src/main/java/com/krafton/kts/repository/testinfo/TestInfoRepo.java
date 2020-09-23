package com.krafton.kts.repository.testinfo;

import com.krafton.kts.testlist.domain.KTS_TEST;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestInfoRepo {

    KTS_TEST create(KTS_TEST test);
    Optional<KTS_TEST> findById(int id);
    Optional<KTS_TEST> findByName(String name);
    List<KTS_TEST> findAll();

}
