package com.krafton.kts.service;

import com.krafton.kts.domains.derived.TEST_REL_TESTCASE_JOIN_TESTCASE;
import com.krafton.kts.domains.entity.KTS_TEST;
import com.krafton.kts.domains.entity.KTS_TESTCASE;
import com.krafton.kts.interfaces.repository.derived.DerivedDomainInterface;
import com.krafton.kts.interfaces.repository.test.AddTestCommand;
import com.krafton.kts.interfaces.repository.test.TestInterface;
import com.krafton.kts.interfaces.repository.testcase.TestcaseInterface;
import com.krafton.kts.interfaces.repository.testreltestcase.SaveTestRelTestcaseCommand;
import com.krafton.kts.interfaces.repository.testreltestcase.TestRelTestcaseInterface;
import com.krafton.kts.interfaces.service.TestEditComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestEditComponentService implements TestEditComponent {

    private final TestInterface testInterface;
    private final DerivedDomainInterface derivedDomainInterface;
    private final TestcaseInterface testcaseInterface;
    private final TestRelTestcaseInterface testRelTestcaseInterface;

    @Override
    public KTS_TEST findTest(String testGuid) {
        return this.testInterface.findTest(testGuid);
    }

    @Override
    public List<TEST_REL_TESTCASE_JOIN_TESTCASE> findTestRelTestcaseJoinTestcase(String testGuid) {
        return this.derivedDomainInterface.findTestRelTestcaseJoinTestcase(testGuid);
    }

    @Override
    public List<KTS_TESTCASE> findAllTestcase() {
        return this.testcaseInterface.findAll();
    }

    @Override
    @Transactional
    public void saveTestRelTestcase(SaveTestRelTestcaseCommand command) {
        try {
            if(command.getRelationList().stream().count() > 0){
                this.testRelTestcaseInterface.saveTestRelTestcase(command);
            }
            this.testInterface.addTest(new AddTestCommand(command.getTestGuid(), command.getTestName(), command.getTestDescription()));
        } catch(Exception e){
            throw e;
        }
    }
}
