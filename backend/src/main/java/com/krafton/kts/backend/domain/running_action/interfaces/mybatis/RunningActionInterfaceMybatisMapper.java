package com.krafton.kts.backend.domain.running_action.interfaces.mybatis;

import com.krafton.kts.interfaces.repository.RunningActionInterface;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface RunningActionInterfaceMybatisMapper extends RunningActionInterface {}
