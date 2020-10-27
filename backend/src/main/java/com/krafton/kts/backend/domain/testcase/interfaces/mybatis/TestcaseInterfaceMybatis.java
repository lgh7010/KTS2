package com.krafton.kts.backend.domain.testcase.interfaces.mybatis;

import com.krafton.kts.commands.testcase.RemoveTestcaseCommand;
import com.krafton.kts.domains.entity.KTS_TESTCASE;
import com.krafton.kts.interfaces.repository.TestcaseInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestcaseInterfaceMybatis implements TestcaseInterface {

    @Autowired
    private TestcaseInterfaceMybatisMapper testcaseInterfaceMybatisMapper;

    @Override
    public void addTestcase(KTS_TESTCASE testcase) {
        this.testcaseInterfaceMybatisMapper.addTestcase(testcase);
    }

    @Override
    public List<KTS_TESTCASE> findAll() {
        return this.testcaseInterfaceMybatisMapper.findAll();
    }

    @Override
    public KTS_TESTCASE findTestcase(String testcaseGuid) {
        return this.testcaseInterfaceMybatisMapper.findTestcase(testcaseGuid);
    }

    @Override
    public void removeTestcase(RemoveTestcaseCommand command) {
        this.testcaseInterfaceMybatisMapper.removeTestcase(command);
    }
}
