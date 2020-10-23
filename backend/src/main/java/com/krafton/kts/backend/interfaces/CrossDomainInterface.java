package com.krafton.kts.backend.interfaces;

import com.krafton.kts.backend.repository.CrossRepository;
import com.krafton.kts.crossdomain.db.TEST_REL_TESTCASE_JOIN_TESTCASE;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CrossDomainInterface {

    private final CrossRepository crossRepository;

    public List<TEST_REL_TESTCASE_JOIN_TESTCASE> findTestRelTestcaseJoinTestcase(String testGuid){
        return this.crossRepository.findTestRelTestcaseJoinTestcase(testGuid);
    }
}
