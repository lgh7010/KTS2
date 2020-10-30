package com.krafton.kts.interfaces.service;

import com.krafton.kts.domains.derived.TestRelTestcaseJoinTestcase;
import java.util.List;

public interface FindTestRelTestcaseJoinTestcaseInterface {
    List<TestRelTestcaseJoinTestcase> findTestRelTestcaseJoinTestcase(String testGuid);
}
