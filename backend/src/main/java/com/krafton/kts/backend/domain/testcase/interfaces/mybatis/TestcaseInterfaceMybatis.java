package com.krafton.kts.backend.domain.testcase.interfaces.mybatis;

import com.krafton.kts.backend.domain.testcase.domain.command.RemoveTestcaseCommand;
import com.krafton.kts.backend.domain.testcase.domain.db.KTS_TESTCASE;
import com.krafton.kts.backend.domain.testcase.interfaces.TestcaseInterface;
import lombok.RequiredArgsConstructor;
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
