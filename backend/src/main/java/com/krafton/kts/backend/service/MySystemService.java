package com.krafton.kts.backend.service;

import com.krafton.kts.backend.service.crossdomain.command.SaveTestcaseCommand;
import com.krafton.kts.backend.domain.action.domain.db.KTS_ACTION;
import com.krafton.kts.backend.domain.property.domain.db.KTS_ACTION_PROPERTY;
import com.krafton.kts.backend.domain.property.domain.db.KTS_ACTION_PROPERTY_TEMPLATE;
import com.krafton.kts.backend.domain.action.domain.db.KTS_ACTION_TEMPLATE;
import com.krafton.kts.backend.domain.test.domain.command.RemoveTestCommand;
import com.krafton.kts.backend.domain.test.domain.db.KTS_TEST;
import com.krafton.kts.backend.domain.test_rel_testcase.domain.command.SaveTestRelTestcaseCommand;
import com.krafton.kts.backend.service.crossdomain.db.TEST_REL_TESTCASE_JOIN_TESTCASE;
import com.krafton.kts.backend.domain.testcase.domain.command.RemoveTestcaseCommand;
import com.krafton.kts.backend.domain.testcase.domain.db.KTS_TESTCASE;

import java.util.List;
import java.util.Map;

public interface MySystemService {
    //transactional
    void saveTestcase(SaveTestcaseCommand command);
    void removeTestcase(RemoveTestcaseCommand command);
    void removeTest(RemoveTestCommand command);
    void saveTestRelTestcase(SaveTestRelTestcaseCommand command);

    //action
    List<KTS_ACTION> findAction(String testcaseGuid);
    Map<String, KTS_ACTION_TEMPLATE> getActionTemplate();

    //property
    List<KTS_ACTION_PROPERTY_TEMPLATE> getPropertyTemplate(String actionId);
    Map<String, List<KTS_ACTION_PROPERTY>> findProperties(String testcaseGuid);

    //test
    List<KTS_TEST> findAllTest();
    KTS_TEST findTest(String testGuid);

    //test_rel_testcase
    List<TEST_REL_TESTCASE_JOIN_TESTCASE> findTestRelTestcaseJoinTestcase(String testGuid);

    //testcase
    List<KTS_TESTCASE> findAll();
    KTS_TESTCASE findTestcase(String testcaseGuid);
}
