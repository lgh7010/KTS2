package com.krafton.kts.repository;

import com.krafton.kts.interfaces.repository.derived.DerivedDomainInterface;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface DerivedDomainInterfaceMybatisMapper extends DerivedDomainInterface {}
