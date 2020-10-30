package com.krafton.kts.service;

import com.krafton.kts.domains.derived.TEST_REL_TESTCASE_JOIN_TESTCASE;
import com.krafton.kts.domains.entity.KTS_TEST;
import com.krafton.kts.domains.entity.KTS_TESTCASE;
import com.krafton.kts.domains.entity.TEST_REL_TESTCASE;
import com.krafton.kts.interfaces.repository.derived.DerivedDomainInterface;
import com.krafton.kts.interfaces.repository.test.AddTestCommand;
import com.krafton.kts.interfaces.repository.test.TestInterface;
import com.krafton.kts.interfaces.repository.testcase.TestcaseInterface;
import com.krafton.kts.interfaces.repository.testreltestcase.SaveTestRelTestcaseCommand;
import com.krafton.kts.interfaces.repository.testreltestcase.TestRelTestcaseInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doAnswer;

@SpringBootTest(classes = TestEditComponentService.class)
public class TestEditComponentServiceTest {
    @Autowired
    private TestEditComponentService testEditComponentService;

    @MockBean
    private TestInterface testInterface;
    @MockBean
    private DerivedDomainInterface derivedDomainInterface;
    @MockBean
    private TestcaseInterface testcaseInterface;

    @MockBean
    private TestRelTestcaseInterface testRelTestcaseInterface;

    @Test
    public void findTest(){
        KTS_TEST test = new KTS_TEST();
        test.setName("test01");
        test.setDeleted("this is test 01");
        test.setTestGuid("GUID1");
        test.setDeleted("N");
        given(testInterface.findTest("GUID1")).willReturn(test);

        KTS_TEST ret = testEditComponentService.findTest("GUID1");

        assertEquals(test.getName(), ret.getName());
        assertEquals(test.getDescription(), ret.getDescription());
        assertEquals(test.getDeleted(), ret.getDeleted());
        assertEquals(test.getTestGuid(), ret.getTestGuid());
    }

    @Test
    public void findTestRelTestcaseJoinTestcase(){
        List<TEST_REL_TESTCASE_JOIN_TESTCASE> list = new ArrayList<>();
        TEST_REL_TESTCASE_JOIN_TESTCASE elem = new TEST_REL_TESTCASE_JOIN_TESTCASE();
        elem.setName("name1");
        given(this.derivedDomainInterface.findTestRelTestcaseJoinTestcase("GUID1")).willReturn(list);

        List<TEST_REL_TESTCASE_JOIN_TESTCASE> ret = this.derivedDomainInterface.findTestRelTestcaseJoinTestcase("GUID1");

        assertEquals(list.stream().count(), ret.stream().count());
        for(int i = 0; i < list.stream().count(); i++){
            assertEquals(list.get(i).getName(), ret.get(i).getName());
        }
    }

    @Test
    public void findAllTestcase(){
        List<KTS_TESTCASE> list = new ArrayList<>();
        KTS_TESTCASE testcase = new KTS_TESTCASE();
        testcase.setName("name1");
        given(this.testcaseInterface.findAll()).willReturn(list);

        List<KTS_TESTCASE> ret = this.testcaseInterface.findAll();

        assertEquals(list.stream().count(), ret.stream().count());
        for(int i = 0; i < list.stream().count(); i++){
            assertEquals(list.get(i).getName(), ret.get(i).getName());
        }
    }
    @Test
    public void saveTestRelTestcase() {
        SaveTestRelTestcaseCommand command = new SaveTestRelTestcaseCommand();
        command.setTestName("name1");
        command.setTestDescription("desc1");
        command.setTestGuid("guid1");
        List<TEST_REL_TESTCASE> rel = new ArrayList<>();
        command.setRelationList(rel);

        doAnswer(invocation -> {
            return null;
        }).when(this.testRelTestcaseInterface).saveTestRelTestcase(new SaveTestRelTestcaseCommand());
        doAnswer(invocation -> {
            return null;
        }).when(this.testInterface).addTest(new AddTestCommand("GUID1", "name1", "desc1"));

        if(command.getRelationList().stream().count() > 0){
            this.testRelTestcaseInterface.saveTestRelTestcase(command);
        }
        this.testInterface.addTest(new AddTestCommand(command.getTestGuid(), command.getTestName(), command.getTestDescription()));
    }
}
