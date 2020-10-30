package com.krafton.kts.interfaces.service;

import com.krafton.kts.domains.entity.KtsTestcase;
import java.util.List;

public interface FindTestcaseServiceInterface {
    KtsTestcase findTestcase(String testcaseGuid);
    List<KtsTestcase> findAllTestcase();
}
