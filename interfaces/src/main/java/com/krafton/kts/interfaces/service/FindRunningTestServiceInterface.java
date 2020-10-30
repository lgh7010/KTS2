package com.krafton.kts.interfaces.service;

import com.krafton.kts.domains.entity.RunningTest;
import java.util.List;

public interface FindRunningTestServiceInterface {
    List<RunningTest> findAllRunningTest();
}
