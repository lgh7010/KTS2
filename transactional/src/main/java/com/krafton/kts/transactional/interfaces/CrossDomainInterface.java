package com.krafton.kts.transactional.interfaces;

import com.krafton.kts.crossdomain.db.TEST_REL_TESTCASE_JOIN_TESTCASE;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrossDomainInterface {
    public List<TEST_REL_TESTCASE_JOIN_TESTCASE> findTestRelTestcaseJoinTestcase(String testGuid){
        return null;
    }
}
