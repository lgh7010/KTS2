package com.krafton.kts.test_rel_testcase.interfaces;

import com.krafton.kts.domain.test_rel_testcase.command.RemoveTestRelTestcaseByTestGuidCommand;
import com.krafton.kts.domain.test_rel_testcase.command.RemoveTestRelTestcaseByTestcaseGuidCommand;
import com.krafton.kts.domain.test_rel_testcase.command.SaveTestRelTestcaseCommand;
import org.springframework.stereotype.Service;

@Service
public class KtsTestRelTestcaseInterface {
    public void saveTestRelTestcase(SaveTestRelTestcaseCommand command){

    }
    public void removeTestRelTestcaseByTestcaseGuid(RemoveTestRelTestcaseByTestcaseGuidCommand command){

    }
    public void removeTestRelTestcaseByTestGuid(RemoveTestRelTestcaseByTestGuidCommand command){

    }
}
