package com.krafton.kts.repository;

import com.krafton.kts.interfaces.repository.derived.DerivedDomainInterface;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CrossDomainInterfaceMybatisMapper extends DerivedDomainInterface {}
