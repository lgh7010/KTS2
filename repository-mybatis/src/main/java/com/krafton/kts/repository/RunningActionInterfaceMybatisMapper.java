package com.krafton.kts.repository;

import com.krafton.kts.interfaces.repository.runningaction.RunningActionInterface;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface RunningActionInterfaceMybatisMapper extends RunningActionInterface {}
