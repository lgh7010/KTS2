package com.krafton.kts.backend.domain.test.interfaces.mybatis;

import com.krafton.kts.commands.test.AddTestCommand;
import com.krafton.kts.commands.test.RemoveTestCommand;
import com.krafton.kts.domains.entity.KTS_TEST;
import com.krafton.kts.interfaces.repository.TestInterface;
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
    public void removeTest(@Param("removeTestCommand") RemoveTestCommand command) {
        this.testInterfaceMybatisMapper.removeTest(command);
    }

    @Override
    public void addTest(AddTestCommand command) {
        this.testInterfaceMybatisMapper.addTest(command);
    }
}
