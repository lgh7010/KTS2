package com.krafton.kts.repository;

import com.krafton.kts.interfaces.repository.property.PropertyInterface;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface PropertyInterfaceMybatisMapper extends PropertyInterface {}
