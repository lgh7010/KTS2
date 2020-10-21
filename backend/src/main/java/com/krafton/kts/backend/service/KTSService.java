package com.krafton.kts.backend.service;

import com.krafton.kts.backend.domain.running_action.domain.db.RUNNING_ACTION;
import com.krafton.kts.backend.domain.running_test.domain.db.RUNNING_TEST;
import com.krafton.kts.backend.service.crossdomain.command.FindRunningActionCommand;
import com.krafton.kts.backend.service.crossdomain.command.OnFinishActionCommand;
import com.krafton.kts.backend.service.crossdomain.command.RunTestCommnad;
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
import com.krafton.kts.backend.service.crossdomain.response.NextTestInstructionResponse;

import java.util.List;
import java.util.Map;

public interface KTSService {
    //transactional
    void saveTestcase(SaveTestcaseCommand command);
    void removeTestcase(RemoveTestcaseCommand command);
    void removeTest(RemoveTestCommand command);
    void saveTestRelTestcase(SaveTestRelTestcaseCommand command);
    NextTestInstructionResponse runTest(RunTestCommnad command);
    NextTestInstructionResponse onFinishAction(OnFinishActionCommand command);

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
    List<KTS_TESTCASE> findAllTestcase();
    KTS_TESTCASE findTestcase(String testcaseGuid);

    //running_test
    List<RUNNING_TEST> findAllRunningTest();
}
