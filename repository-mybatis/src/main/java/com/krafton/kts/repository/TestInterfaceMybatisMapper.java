package com.krafton.kts.repository;

import com.krafton.kts.interfaces.repository.test.TestInterface;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface TestInterfaceMybatisMapper extends TestInterface {}
