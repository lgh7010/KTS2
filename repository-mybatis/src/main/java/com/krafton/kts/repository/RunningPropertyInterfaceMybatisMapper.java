package com.krafton.kts.repository;

import com.krafton.kts.interfaces.repository.runningproperty.RunningPropertyInterface;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface RunningPropertyInterfaceMybatisMapper extends RunningPropertyInterface {}
