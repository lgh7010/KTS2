package com.krafton.kts.interfaces.service;

import com.krafton.kts.domains.entity.KtsTest;
import java.util.List;

public interface FindTestServiceInterface {
    List<KtsTest> findAllTest();
    KtsTest findTest(String testGuid);
}
