package com.krafton.kts.module_repository.testinfo;

import com.krafton.kts.module_testlist.domain.KTS_TEST;

import java.util.List;
import java.util.Optional;

public class TestInfoRepo_jpa implements TestInfoRepo {
    @Override
    public KTS_TEST create(KTS_TEST test) {
        return null;
    }

    @Override
    public Optional<KTS_TEST> findById(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<KTS_TEST> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<KTS_TEST> findAll() {
        return null;
    }
}
