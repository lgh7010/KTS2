package com.krafton.kts.backend.test_rel_testcase.domain;

import lombok.Data;

import java.util.List;

@Data
public class TestRelTestcaseSaveCommand {
    private List<TEST_REL_TESTCASE> relationList;
    private int testSeq;
    private String testName;
    private String testDescription;
    private List<Integer> removeRelationSeqList;
}
