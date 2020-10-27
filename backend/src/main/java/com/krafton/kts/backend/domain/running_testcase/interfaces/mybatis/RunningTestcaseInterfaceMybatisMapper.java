package com.krafton.kts.backend.domain.running_testcase.interfaces.mybatis;

import com.krafton.kts.interfaces.repository.RunningTestcaseInterface;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface RunningTestcaseInterfaceMybatisMapper extends RunningTestcaseInterface {}
