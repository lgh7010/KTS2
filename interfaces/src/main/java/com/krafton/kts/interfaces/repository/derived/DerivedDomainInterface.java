package com.krafton.kts.interfaces.repository.derived;

import com.krafton.kts.domains.derived.TestRelTestcaseJoinTestcase;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface DerivedDomainInterface {
    List<TestRelTestcaseJoinTestcase> findTestRelTestcaseJoinTestcase(String testGuid);
}
