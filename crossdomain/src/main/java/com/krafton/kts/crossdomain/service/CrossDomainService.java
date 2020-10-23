package com.krafton.kts.crossdomain.service;

import com.krafton.kts.crossdomain.db.TEST_REL_TESTCASE_JOIN_TESTCASE;
import com.krafton.kts.crossdomain.interfaces.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CrossDomainService {


    private final CrossDomainInterface crossDomainInterface;

    public List<TEST_REL_TESTCASE_JOIN_TESTCASE> findTestRelTestcaseJoinTestcase(String testGuid){
        return this.crossDomainInterface.findTestRelTestcaseJoinTestcase(testGuid);
    }
}
