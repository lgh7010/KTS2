package com.krafton.kts.backend.crossdomain.interfaces.mybatis;

import com.krafton.kts.domains.derived.TEST_REL_TESTCASE_JOIN_TESTCASE;
import com.krafton.kts.interfaces.repository.CrossDomainInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CrossDomainInterfaceMybatis implements CrossDomainInterface {

    @Autowired
    private CrossDomainInterfaceMybatisMapper crossDomainInterfaceMybatisMapper;

    @Override
    public List<TEST_REL_TESTCASE_JOIN_TESTCASE> findTestRelTestcaseJoinTestcase(String testGuid) {
        return this.crossDomainInterfaceMybatisMapper.findTestRelTestcaseJoinTestcase(testGuid);
    }
}
