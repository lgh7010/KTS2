package com.krafton.kts.backend.domain.test_rel_testcase.service.impl.mybatis;

import com.krafton.kts.backend.domain.test_rel_testcase.domain.command.TestRelTestcaseSaveCommand;
import com.krafton.kts.backend.domain.test_rel_testcase.domain.db.TestRelTestcaseDerived;
import com.krafton.kts.backend.domain.test_rel_testcase.service.impl.TestRelTestcaseInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestRelTestcaseInterfaceMybatis implements TestRelTestcaseInterface {

    @Autowired
    private TestRelTestcaseInterfaceMybatisMapper testRelTestcaseInterfaceMybatisMapper;

    @Override
    public List<TestRelTestcaseDerived> findTestRelTestcaseDerived(int testSeq) {
        System.out.println("findTestRelTestcaseDerived : " + testSeq);
        return this.testRelTestcaseInterfaceMybatisMapper.findTestRelTestcaseDerived(testSeq);
    }

    @Override
    public void saveTestRelTestcase(TestRelTestcaseSaveCommand command) {
        System.out.println(command);
        this.testRelTestcaseInterfaceMybatisMapper.saveTestRelTestcase(command);
    }
}
