package com.krafton.kts.crossdomain.interfaces;

import com.krafton.kts.crossdomain.repository.CrossDomainRepository;
import com.krafton.kts.crossdomain.db.TEST_REL_TESTCASE_JOIN_TESTCASE;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CrossDomainInterface {

    private final CrossDomainRepository crossRepository;

    public List<TEST_REL_TESTCASE_JOIN_TESTCASE> findTestRelTestcaseJoinTestcase(String testGuid){
        return this.crossRepository.findTestRelTestcaseJoinTestcase(testGuid);
    }
}
