package com.krafton.kts.service;

import com.krafton.kts.domains.entity.KTS_TEST;
import com.krafton.kts.interfaces.repository.test.RemoveTestCommand;
import com.krafton.kts.interfaces.repository.test.TestInterface;
import com.krafton.kts.interfaces.repository.testreltestcase.RemoveTestRelTestcaseByTestGuidCommand;
import com.krafton.kts.interfaces.repository.testreltestcase.TestRelTestcaseInterface;
import com.krafton.kts.interfaces.service.TestListComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestListComponentService implements TestListComponent {

    private final TestInterface testInterface;
    private final TestRelTestcaseInterface testRelTestcaseInterface;

    @Override
    public List<KTS_TEST> findAllTest() {
        return this.testInterface.findAllTest();
    }

    @Override
    @Transactional
    public void removeTest(RemoveTestCommand command) {
        try {
            this.testInterface.removeTest(command);
            this.testRelTestcaseInterface.removeTestRelTestcaseByTestGuid(new RemoveTestRelTestcaseByTestGuidCommand(command.getTestGuid()));
        } catch (Exception e){
            throw e;
        }
    }
}
