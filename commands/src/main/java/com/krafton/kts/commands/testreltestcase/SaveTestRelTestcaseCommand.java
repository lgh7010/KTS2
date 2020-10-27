package com.krafton.kts.commands.testreltestcase;

import com.krafton.kts.domains.entity.TEST_REL_TESTCASE;
import lombok.Data;

import java.util.List;

@Data
public class SaveTestRelTestcaseCommand {
    private List<TEST_REL_TESTCASE> relationList;
    private String testGuid;
    private String testName;
    private String testDescription;
}
