package com.krafton.kts.interfaces.repository.testreltestcase;

import com.krafton.kts.domains.entity.TestRelTestcase;
import lombok.Data;

import java.util.List;

@Data
public class SaveTestRelTestcaseCommand {
    private List<TestRelTestcase> relationList;
    private String testGuid;
    private String testName;
    private String testDescription;
}
