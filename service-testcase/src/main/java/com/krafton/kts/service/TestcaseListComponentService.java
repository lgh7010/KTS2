package com.krafton.kts.service;

import com.krafton.kts.domains.entity.KTS_TESTCASE;
import com.krafton.kts.interfaces.repository.action.ActionInterface;
import com.krafton.kts.interfaces.repository.action.RemoveActionCommand;
import com.krafton.kts.interfaces.repository.property.PropertyInterface;
import com.krafton.kts.interfaces.repository.property.RemovePropertiesCommand;
import com.krafton.kts.interfaces.repository.testcase.RemoveTestcaseCommand;
import com.krafton.kts.interfaces.repository.testcase.TestcaseInterface;
import com.krafton.kts.interfaces.repository.testreltestcase.RemoveTestRelTestcaseByTestcaseGuidCommand;
import com.krafton.kts.interfaces.repository.testreltestcase.TestRelTestcaseInterface;
import com.krafton.kts.interfaces.service.TestcaseListComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestcaseListComponentService implements TestcaseListComponent {

    private final TestcaseInterface testcaseInterface;
    private final ActionInterface actionInterface;
    private final TestRelTestcaseInterface testRelTestcaseInterface;
    private final PropertyInterface propertyInterface;

    @Override
    public List<KTS_TESTCASE> findAllTestcase() {
        return this.testcaseInterface.findAll();
    }

    @Override
    @Transactional
    public void removeTestcase(RemoveTestcaseCommand command) {
        try {
            this.testcaseInterface.removeTestcase(command);
            this.actionInterface.removeAction(new RemoveActionCommand(command.getTestcaseGuid()));
            this.testRelTestcaseInterface.removeTestRelTestcaseByTestcaseGuid(new RemoveTestRelTestcaseByTestcaseGuidCommand(command.getTestcaseGuid()));
            this.propertyInterface.removeProperties(new RemovePropertiesCommand(command.getTestcaseGuid()));
        } catch(Exception e){
            throw e;
        }
    }
}
