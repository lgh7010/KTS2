package com.krafton.kts.backend.domain.test_rel_testcase.interfaces.mybatis;

import com.krafton.kts.commands.testreltestcase.RemoveTestRelTestcaseByTestGuidCommand;
import com.krafton.kts.commands.testreltestcase.RemoveTestRelTestcaseByTestcaseGuidCommand;
import com.krafton.kts.commands.testreltestcase.SaveTestRelTestcaseCommand;
import com.krafton.kts.interfaces.repository.TestRelTestcaseInterface;
import org.springframework.beans.factory.annotation.Autowired;

public class TestRelTestcaseInterfaceMybatis implements TestRelTestcaseInterface {

    @Autowired
    private TestRelTestcaseInterfaceMybatisMapper testRelTestcaseInterfaceMybatisMapper;

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
