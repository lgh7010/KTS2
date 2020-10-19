package com.krafton.kts.backend.domain.test_rel_testcase.interfaces.mybatis;

import com.krafton.kts.backend.domain.test_rel_testcase.domain.command.RemoveTestRelTestcaseByTestGuidCommand;
import com.krafton.kts.backend.domain.test_rel_testcase.domain.command.RemoveTestRelTestcaseByTestcaseGuidCommand;
import com.krafton.kts.backend.domain.test_rel_testcase.domain.command.SaveTestRelTestcaseCommand;
import com.krafton.kts.backend.domain.test_rel_testcase.domain.db.TestRelTestcaseDerived;
import com.krafton.kts.backend.domain.test_rel_testcase.interfaces.TestRelTestcaseInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestRelTestcaseInterfaceMybatis implements TestRelTestcaseInterface {

    @Autowired
    private TestRelTestcaseInterfaceMybatisMapper testRelTestcaseInterfaceMybatisMapper;

    @Override
    public List<TestRelTestcaseDerived> findTestRelTestcaseDerived(String testGuid) {
        System.out.println("findTestRelTestcaseDerived : " + testGuid);
        return this.testRelTestcaseInterfaceMybatisMapper.findTestRelTestcaseDerived(testGuid);
    }

    @Override
    public void saveTestRelTestcase(SaveTestRelTestcaseCommand command) {
        System.out.println(command);
        this.testRelTestcaseInterfaceMybatisMapper.saveTestRelTestcase(command);
    }

    @Override
    public void removeTestRelTestcase(RemoveTestRelTestcaseByTestcaseGuidCommand command) {
        this.testRelTestcaseInterfaceMybatisMapper.removeTestRelTestcase(command);
    }

    @Override
    public void removeTestRelTestcase(RemoveTestRelTestcaseByTestGuidCommand command) {
        this.testRelTestcaseInterfaceMybatisMapper.removeTestRelTestcase(command);
    }
}
