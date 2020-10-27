package com.krafton.kts.repository;

import com.krafton.kts.interfaces.repository.runningtestcase.RunningTestcaseInterface;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface RunningTestcaseInterfaceMybatisMapper extends RunningTestcaseInterface {}
