package com.krafton.kts.backend.domain.test_rel_testcase.service.impl.mybatis;

import com.krafton.kts.backend.domain.test_rel_testcase.service.impl.TestRelTestcaseInterface;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TestRelTestcaseInterfaceMybatisMapper extends TestRelTestcaseInterface {}
