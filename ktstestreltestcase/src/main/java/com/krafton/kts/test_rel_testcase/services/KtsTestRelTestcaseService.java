package com.krafton.kts.test_rel_testcase.services;

import com.krafton.kts.domain.test_rel_testcase.command.RemoveTestRelTestcaseByTestGuidCommand;
import com.krafton.kts.domain.test_rel_testcase.command.RemoveTestRelTestcaseByTestcaseGuidCommand;
import com.krafton.kts.domain.test_rel_testcase.command.SaveTestRelTestcaseCommand;
import com.krafton.kts.test_rel_testcase.interfaces.KtsTestRelTestcaseInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KtsTestRelTestcaseService {

    private final KtsTestRelTestcaseInterface testRelTestcaseInterface;

    public void saveTestRelTestcase(SaveTestRelTestcaseCommand command){
        this.testRelTestcaseInterface.saveTestRelTestcase(command);
    }
    public void removeTestRelTestcaseByTestcaseGuid(RemoveTestRelTestcaseByTestcaseGuidCommand command){
        this.testRelTestcaseInterface.removeTestRelTestcaseByTestcaseGuid(command);
    }
    public void removeTestRelTestcaseByTestGuid(RemoveTestRelTestcaseByTestGuidCommand command){
        this.testRelTestcaseInterface.removeTestRelTestcaseByTestGuid(command);
    }
}
