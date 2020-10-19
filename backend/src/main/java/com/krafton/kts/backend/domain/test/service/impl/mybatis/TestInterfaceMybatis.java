package com.krafton.kts.backend.domain.test.service.impl.mybatis;

import com.krafton.kts.backend.domain.test.domain.command.AddTestCommand;
import com.krafton.kts.backend.domain.test.domain.command.RemoveTestCommand;
import com.krafton.kts.backend.domain.test.domain.db.KTS_TEST;
import com.krafton.kts.backend.domain.test.service.impl.TestInterface;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class TestInterfaceMybatis implements TestInterface {

    @Autowired
    private TestInterfaceMybatisMapper testInterfaceMybatisMapper;

    @Override
    public KTS_TEST findTest(String testGuid) {
        return this.testInterfaceMybatisMapper.findTest(testGuid);
    }

    @Override
    public List<KTS_TEST> findAllTest() {
        return this.testInterfaceMybatisMapper.findAllTest();
    }

    @Override
    @Transactional
    public void removeTest(@Param("removeTestCommand")RemoveTestCommand command) {
        this.testInterfaceMybatisMapper.removeTest(command);
    }

    @Override
    public void addTest(AddTestCommand command) {
        this.addTest(command);
    }
}
