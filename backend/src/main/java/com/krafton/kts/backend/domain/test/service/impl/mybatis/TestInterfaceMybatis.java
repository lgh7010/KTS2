package com.krafton.kts.backend.domain.test.service.impl.mybatis;

import com.krafton.kts.backend.domain.test.domain.command.RemoveTestCommand;
import com.krafton.kts.backend.domain.test.domain.db.KTS_TEST;
import com.krafton.kts.backend.domain.test.service.impl.TestInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestInterfaceMybatis implements TestInterface {

    @Autowired
    private TestInterfaceMybatisMapper testInterfaceMybatisMapper;

    @Override
    public KTS_TEST findTest(int testSeq) {
        return this.testInterfaceMybatisMapper.findTest(testSeq);
    }

    @Override
    public List<KTS_TEST> findAllTest() {
        return this.testInterfaceMybatisMapper.findAllTest();
    }

    @Override
    public void removeTest(RemoveTestCommand command) {
        this.testInterfaceMybatisMapper.removeTest(command);
    }
}
