package com.krafton.kts.backend.domain.running_test.interfaces.mybatis;

import com.krafton.kts.interfaces.repository.RunningTestInterface;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface RunningTestInterfaceMybatisMapper extends RunningTestInterface {}
