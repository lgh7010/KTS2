package com.krafton.kts.interfaces.service;

import com.krafton.kts.domains.entity.RUNNING_TEST;

import java.util.List;

public interface TestDashboard {
    List<RUNNING_TEST> findAllRunningTest();
}
