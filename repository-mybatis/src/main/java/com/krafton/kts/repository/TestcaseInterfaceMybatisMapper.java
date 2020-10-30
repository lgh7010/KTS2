package com.krafton.kts.repository;

import com.krafton.kts.interfaces.repository.testcase.TestcaseInterface;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface TestcaseInterfaceMybatisMapper extends TestcaseInterface {}
