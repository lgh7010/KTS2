package com.krafton.kts.backend.domain.action.interfaces.mybatis;

import com.krafton.kts.backend.domain.action.interfaces.ActionInterface;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ActionInterfaceMybatisMapper extends ActionInterface {}
