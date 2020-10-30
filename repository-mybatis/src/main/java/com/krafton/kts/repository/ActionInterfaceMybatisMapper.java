package com.krafton.kts.repository;

import com.krafton.kts.interfaces.repository.action.ActionInterface;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface ActionInterfaceMybatisMapper extends ActionInterface {}
