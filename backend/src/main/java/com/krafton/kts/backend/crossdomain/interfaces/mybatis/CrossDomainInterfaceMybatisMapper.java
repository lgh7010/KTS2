package com.krafton.kts.backend.crossdomain.interfaces.mybatis;

import com.krafton.kts.interfaces.repository.CrossDomainInterface;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CrossDomainInterfaceMybatisMapper extends CrossDomainInterface {}
