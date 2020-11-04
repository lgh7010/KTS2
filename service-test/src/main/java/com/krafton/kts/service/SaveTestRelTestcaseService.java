package com.krafton.kts.service;

import com.krafton.kts.interfaces.repository.test.AddTestCommand;
import com.krafton.kts.interfaces.repository.test.TestInterface;
import com.krafton.kts.interfaces.repository.testreltestcase.SaveTestRelTestcaseCommand;
import com.krafton.kts.interfaces.repository.testreltestcase.TestRelTestcaseInterface;
import com.krafton.kts.interfaces.service.SaveTestRelTestcaseServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SaveTestRelTestcaseService implements SaveTestRelTestcaseServiceInterface {

    private final TestInterface testInterface;
    private final TestRelTestcaseInterface testRelTestcaseInterface;

    @Override
    @Transactional
    public void saveTestRelTestcase(SaveTestRelTestcaseCommand command) {
        try {
            if(command.getRelationList() != null && command.getRelationList().stream().count() > 0){
                this.testRelTestcaseInterface.saveTestRelTestcase(command);
            }
            this.testInterface.addTest(new AddTestCommand(command.getTestGuid(), command.getTestName(), command.getTestDescription()));
        } catch(Exception e){
            throw e;
        }
    }
}
