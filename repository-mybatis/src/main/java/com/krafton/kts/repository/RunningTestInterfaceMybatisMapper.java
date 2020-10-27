package com.krafton.kts.repository;

import com.krafton.kts.interfaces.repository.runningtest.RunningTestInterface;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface RunningTestInterfaceMybatisMapper extends RunningTestInterface {}
