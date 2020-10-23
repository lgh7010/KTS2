package com.krafton.kts.transactional.interfaces;

import com.krafton.kts.domain.test_rel_testcase.command.RemoveTestRelTestcaseByTestGuidCommand;
import com.krafton.kts.domain.test_rel_testcase.command.RemoveTestRelTestcaseByTestcaseGuidCommand;
import com.krafton.kts.domain.test_rel_testcase.command.SaveTestRelTestcaseCommand;
import org.springframework.stereotype.Service;

@Service
public class TestRelTestcaseInterface {
    public void saveTestRelTestcase(SaveTestRelTestcaseCommand command){

    }
    public void removeTestRelTestcaseByTestcaseGuid(RemoveTestRelTestcaseByTestcaseGuidCommand command){

    }
    public void removeTestRelTestcaseByTestGuid(RemoveTestRelTestcaseByTestGuidCommand command){

    }
}
