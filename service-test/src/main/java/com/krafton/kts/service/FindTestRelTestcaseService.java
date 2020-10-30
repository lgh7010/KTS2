package com.krafton.kts.service;

import com.krafton.kts.domains.derived.TestRelTestcaseJoinTestcase;
import com.krafton.kts.interfaces.repository.derived.DerivedDomainInterface;
import com.krafton.kts.interfaces.service.FindTestRelTestcaseJoinTestcaseInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindTestRelTestcaseService implements FindTestRelTestcaseJoinTestcaseInterface {

    private final DerivedDomainInterface derivedDomainInterface;

    @Override
    public List<TestRelTestcaseJoinTestcase> findTestRelTestcaseJoinTestcase(String testGuid) {
        return this.derivedDomainInterface.findTestRelTestcaseJoinTestcase(testGuid);
    }
}
