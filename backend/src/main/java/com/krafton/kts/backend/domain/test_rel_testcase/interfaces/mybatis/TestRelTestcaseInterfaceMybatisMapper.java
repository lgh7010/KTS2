package com.krafton.kts.backend.domain.test_rel_testcase.interfaces.mybatis;

import com.krafton.kts.backend.domain.test_rel_testcase.interfaces.TestRelTestcaseInterface;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TestRelTestcaseInterfaceMybatisMapper extends TestRelTestcaseInterface {}
