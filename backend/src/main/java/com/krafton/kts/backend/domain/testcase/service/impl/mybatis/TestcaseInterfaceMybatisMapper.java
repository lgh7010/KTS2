package com.krafton.kts.backend.domain.testcase.service.impl.mybatis;

import com.krafton.kts.backend.domain.testcase.service.impl.TestcaseInterface;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TestcaseInterfaceMybatisMapper extends TestcaseInterface {}
