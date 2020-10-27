package com.krafton.kts.backend.domain.running_property.interfaces.mybatis;

import com.krafton.kts.interfaces.repository.RunningPropertyInterface;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface RunningPropertyInterfaceMybatisMapper extends RunningPropertyInterface {}
