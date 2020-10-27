package com.krafton.kts.repository;

import com.krafton.kts.interfaces.repository.testreltestcase.TestRelTestcaseInterface;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TestRelTestcaseInterfaceMybatisMapper extends TestRelTestcaseInterface {}
