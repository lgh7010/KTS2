package com.krafton.kts.backend.domain.property.interfaces.mybatis;

import com.krafton.kts.backend.domain.property.interfaces.PropertyInterface;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PropertyInterfaceMybatisMapper extends PropertyInterface {}
