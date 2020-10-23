package com.krafton.kts.backend.service;

import com.krafton.kts.backend.interfaces.*;
import com.krafton.kts.domain.action.command.*;
import com.krafton.kts.domain.action.db.KTS_ACTION;
import com.krafton.kts.domain.property.command.FindPropertiesCommand;
import com.krafton.kts.domain.property.command.RemovePropertiesCommand;
import com.krafton.kts.domain.property.command.SavePropertiesCommand;
import com.krafton.kts.domain.property.db.KTS_ACTION_PROPERTY;
import com.krafton.kts.domain.running_action.command.AddRunningActionCommand;
import com.krafton.kts.domain.running_action.db.RUNNING_ACTION;
import com.krafton.kts.domain.running_property.command.AddRunningPropertyCommand;
import com.krafton.kts.domain.running_property.db.RUNNING_PROPERTY;
import com.krafton.kts.domain.running_test.db.RUNNING_TEST;
import com.krafton.kts.domain.running_testcase.command.AddRunningTestcaseCommand;
import com.krafton.kts.domain.running_testcase.db.RUNNING_TESTCASE;
import com.krafton.kts.domain.test.command.AddTestCommand;
import com.krafton.kts.domain.test.command.RemoveTestCommand;
import com.krafton.kts.domain.test.db.KTS_TEST;
import com.krafton.kts.domain.test_rel_testcase.command.RemoveTestRelTestcaseByTestGuidCommand;
import com.krafton.kts.domain.test_rel_testcase.command.RemoveTestRelTestcaseByTestcaseGuidCommand;
import com.krafton.kts.domain.test_rel_testcase.command.SaveTestRelTestcaseCommand;
import com.krafton.kts.crossdomain.command.FindRunningActionCommand;
import com.krafton.kts.crossdomain.command.OnFinishActionCommand;
import com.krafton.kts.crossdomain.command.RunTestCommand;
import com.krafton.kts.crossdomain.db.TEST_REL_TESTCASE_JOIN_TESTCASE;
import com.krafton.kts.domain.testcase.command.RemoveTestcaseCommand;
import com.krafton.kts.crossdomain.command.SaveTestcaseCommand;
import com.krafton.kts.crossdomain.response.NextTestInstructionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CrossDomainService {


    private final CrossDomainInterface crossDomainInterface;


}
