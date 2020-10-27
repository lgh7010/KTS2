package com.krafton.kts.backend.domain.test.interfaces.mybatis;

import com.krafton.kts.interfaces.repository.TestInterface;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TestInterfaceMybatisMapper extends TestInterface {}
