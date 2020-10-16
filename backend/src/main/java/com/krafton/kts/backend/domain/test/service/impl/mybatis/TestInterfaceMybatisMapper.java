package com.krafton.kts.backend.domain.test.service.impl.mybatis;

import com.krafton.kts.backend.domain.test.service.impl.TestInterface;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TestInterfaceMybatisMapper extends TestInterface {}
