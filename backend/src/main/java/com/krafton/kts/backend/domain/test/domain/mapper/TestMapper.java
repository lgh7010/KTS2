package com.krafton.kts.backend.domain.test.domain.mapper;

import com.krafton.kts.backend.domain.test.domain.db.KTS_TEST;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TestMapper {
    List<KTS_TEST> getTest();
}
