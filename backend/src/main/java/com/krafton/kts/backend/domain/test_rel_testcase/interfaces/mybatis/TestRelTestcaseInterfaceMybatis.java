package com.krafton.kts.backend.domain.test_rel_testcase.interfaces.mybatis;

import com.krafton.kts.backend.domain.test_rel_testcase.domain.command.RemoveTestRelTestcaseByTestGuidCommand;
import com.krafton.kts.backend.domain.test_rel_testcase.domain.command.RemoveTestRelTestcaseByTestcaseGuidCommand;
import com.krafton.kts.backend.domain.test_rel_testcase.domain.command.SaveTestRelTestcaseCommand;
import com.krafton.kts.backend.service.crossdomain.db.TEST_REL_TESTCASE_JOIN_TESTCASE;
import com.krafton.kts.backend.domain.test_rel_testcase.interfaces.TestRelTestcaseInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestRelTestcaseInterfaceMybatis implements TestRelTestcaseInterface {

    @Autowired
    private TestRelTestcaseInterfaceMybatisMapper testRelTestcaseInterfaceMybatisMapper;

    @Override
    public List<TEST_REL_TESTCASE_JOIN_TESTCASE> findTestRelTestcaseJoinTestcase(String testGuid) {
        return this.testRelTestcaseInterfaceMybatisMapper.findTestRelTestcaseJoinTestcase(testGuid);
    }

    @Override
    public void saveTestRelTestcase(SaveTestRelTestcaseCommand command) {
        System.out.println(command);
        this.testRelTestcaseInterfaceMybatisMapper.saveTestRelTestcase(command);
    }

    @Override
    public void removeTestRelTestcaseByTestcaseGuid(RemoveTestRelTestcaseByTestcaseGuidCommand command) {
        this.testRelTestcaseInterfaceMybatisMapper.removeTestRelTestcaseByTestcaseGuid(command);
    }

    @Override
    public void removeTestRelTestcaseByTestGuid(RemoveTestRelTestcaseByTestGuidCommand command) {
        this.testRelTestcaseInterfaceMybatisMapper.removeTestRelTestcaseByTestGuid(command);
    }
}
