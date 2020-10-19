package com.krafton.kts.backend.domain.test_rel_testcase.domain.command;

import com.krafton.kts.backend.domain.test_rel_testcase.domain.db.TEST_REL_TESTCASE;
import lombok.Data;

import java.util.List;

@Data
public class SaveTestRelTestcaseCommand {
    private List<TEST_REL_TESTCASE> relationList;
    private String testGuid;
    private String testName;
    private String testDescription;
}
